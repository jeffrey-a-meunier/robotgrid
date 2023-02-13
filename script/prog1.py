#!/usr/local/bin/python3

import socket
import sys
import threading
import time

PORT = 43210

SETUP = [
    "World CreateMovingBase R1 3 2 East"
]

SETUP_1 = [
    "World CreateMovingBase R1 3 2 East",
    "R1 PowerOn",
    "World CreateRotatingBase Base1 4 2",
    "World CreateArm Arm1 4 2 South",
    "Base1 PowerOn",
    "Arm1 PowerOn",
    "World CreateRotatingBase Base2 4 5",
    "World CreateArm Arm2 4 5 North",
    "Base2 PowerOn",
    "Arm2 PowerOn",
    "World CreateConveyor Conveyor1 5 5 West",
    "World CreateConveyor Conveyor2 5 4 West",
    "World CreateConveyor Conveyor3 5 3 West",
    "World CreateGroup ConveyorGroup1",
    "ConveyorGroup1 Add Conveyor1 Conveyor2 Conveyor3",
    "World CreateTable Table1 5 2",
    "World CreateDrone D1 5 0",
    "World CreateWidget Widget3 5 1"
]

PROGRAM = [
    "R1 PowerOn",
    "R1 MoveForward"
]

class Immediate:
    def apply(self, cmd):
        cmd.isAsync = False

class Asynchronous:
    def apply(self, cmd):
        cmd.isAsync = True

IMMED = Immediate()
ASYNC = Asynchronous()

PROGRAM_1 = [
    ("D1 PowerOn", IMMED),
    ("D1 MoveEast", ASYNC),
    ("D1 PickUp", ASYNC),
    ("D1 MoveEast", ASYNC),
    ("D1 Drop", ASYNC),
    ("D1 MoveWest", ASYNC),
    ("D1 PowerOff", IMMED),
    ("Arm1 Extend", ASYNC),
    ("Arm1 Grip", ASYNC),
    ("Arm1 Retract", ASYNC),
    ("Base1 RotateLeft", ASYNC),
    ("Base1 RotateLeft", ASYNC),
    ("Arm1 Extend", ASYNC),
    ("Arm1 Release", ASYNC),
    ("Arm1 Retract", ASYNC),
    ("Base1 RotateRight", ASYNC),
    ("Base1 RotateRight", ASYNC),
    ("R1 MoveForward", ASYNC),
    ("R1 MoveForward", ASYNC),
    ("R1 MoveForward", ASYNC),
    ("Arm2 Extend", ASYNC),
    ("Arm2 Grip", ASYNC),
    ("Arm2 Retract", ASYNC),
    ("R1 RotateLeft", ASYNC),
    ("R1 RotateLeft", ASYNC),
    ("R1 MoveForward", ASYNC),
    ("R1 MoveForward", ASYNC),
    ("R1 MoveForward", ASYNC),
    ("R1 RotateRight", ASYNC),
    ("R1 RotateRight", ASYNC),
    ("Base2 RotateRight", ASYNC),
    ("Base2 RotateRight", ASYNC),
    ("Arm2 Extend", ASYNC),
    ("Arm2 Release", ASYNC),
    ("Arm2 Retract", ASYNC),
    ("Base2 RotateLeft", ASYNC),
    ("Base2 RotateLeft", ASYNC),
    ("ConveyorGroup1 PowerOn", IMMED)
]

class Success:
    pass

class Failure:
    pass

class Started:
    pass

class Command:
    def __init__(self, string):
        self.string = string
        self.isAsync = False
        self.isStarted = False
        self.isComplete = False
        self.isError = False
        self.errorMessage = None
        self.condition = threading.Condition()

    def wait(self):
        if self.isAsync and not self.isComplete:
            with self.condition:
                self.condition.wait()

    def __str__(self):
        return "Command{" + str(self.string) + \
            ", isStarted=" + str(self.isStarted) + \
            ", isComplete=" + str(self.isComplete) + \
            ", isError=" + str(self.isError) + \
            "}"

class Server:
    ASYNC_COMMANDS = {}

    def __init__(self, host, port):
        self.cmdSocket = socket.socket()
        self.cmdSocketIsConnected = False
        try:
            print("Server opening connection to", host, port)
            self.cmdSocket.connect((host, port))
            self.cmdSocketIsConnected = True
            print("Server starting info thread")
            self.infoThread = threading.Thread(target=self.handleInfoSocket, args=(host, port+1))
            self.infoThread.start()
        except:
            print("Unable to connect to simulation on port", port)

    def handleInfoSocket(self, host, port):
        print("handleInfoSocket called with", host, port)
        self.infoSocket = socket.socket()
        try:
            self.infoSocket.connect((host, port))
            print("handleInfoSocket Connected to simulation on port", port)
            while True:
                infoBytes = self.infoSocket.recv(1024)
                infoString = infoBytes.decode('utf-8')
                print("handleInfoSocket got infoString", infoString)
                if not infoString:
                    break
                self.decodeInfoString(infoString)
        except Exception as exn:
            print("Info socket exception", exn)

    def decodeInfoString(self, infoString):
        print("Server.decodeInfoString got string '", infoString, "'", sep='')

    def sendCommand(self, command):
        cmdBytes = str.encode(command.string + '\n')
        sendRes = self.cmdSocket.send(cmdBytes)
        print("sendCommand res =", sendRes)
        replyBytes = self.cmdSocket.recv(1024)
        replyString = replyBytes.decode('utf-8')
        if replyString.startswith(':OK'):
            command.isStarted = True
            command.isComplete = True
        elif replyString.startswith(':STARTED'):
            replyStringParts = replyString.split()
            commandNumber = replyStringParts[1]
            ASYNC_COMMANDS[commandNumber] = command
            command.isStarted = True
        elif replyString.startswith(':ERROR'):
            command.isError = True
        elif relpyString.startsWith(':OFF'):
            command.isError = True
            command.errorMessage = "Device is off"
        else:
            print("Server.sendCommand did not understand reply:", replyString)

def setup(server):
    for cmdString in SETUP:
        cmd = Command(cmdString)
        print("SETUP:", cmd)
        server.sendCommand(cmd)

def runProgram(server):
    for (cmdString, synchrony) in PROGRAM:
        cmd = Command(cmdString)
        synchrony.apply(cmd)
        print("PROGRAM:", cmd)
        server.sendCommand(cmd)
        cmd.wait()

def main(args):
    server = Server('127.0.0.1', PORT)
    if not server.cmdSocketIsConnected:
        exit(1)
    setup(server)
    time.sleep(1)
    runProgram(server)

if __name__ == '__main__':
    main(sys.argv)
