#!/usr/local/bin/python3

import socket
import sys
import threading
import time

SERVER = '127.0.0.1'
PORT = 43210

SETUP = [
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

class Immediate:
    def apply(self, cmd):
        cmd.isAsync = False

class Asynchronous:
    def apply(self, cmd):
        cmd.isAsync = True

IMMED = Immediate()
ASYNC = Asynchronous()

PROGRAM = [
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
        self.isCompleted = False
        self.isError = False
        self.errorMessage = None
        self.condition = threading.Condition()

    def wait(self):
        if self.isAsync and not self.isCompleted:
            with self.condition:
                self.condition.wait()

    def notify(self):
        with self.condition:
            self.condition.notify()

    def __str__(self):
        return "Command{" + str(self.string) + \
            ", isStarted=" + str(self.isStarted) + \
            ", isCompleted=" + str(self.isCompleted) + \
            ", isError=" + str(self.isError) + \
            "}"

class Server:
    ASYNC_COMMANDS_STARTED = {}
    ASYNC_COMMANDS_STARTED_LOCK = threading.Lock()
    DEVICES = {}
    DEVICE_SUBSCRIBERS = {}
    CELLS = {}
    CELL_SUBSCRIBERS = {}

    def __init__(self, host, port):
        self.cmdSocket = socket.socket()
        self.cmdSocketIsConnected = False
        try:
            self.cmdSocket.connect((host, port))
            self.cmdSocketIsConnected = True
            self.infoThread = threading.Thread(target=self.handleInfoSocket, args=(host, port+1))
            self.infoThread.start()
        except:
            print("Unable to connect to simulation on port", port)

    def readLine(self, skt):
        chars = []
        while True:
            # the server had better send utf-8
            char = skt.recv(1).decode('utf-8')
            if char == '\n':
                break
            if char == '':
                return None
            chars.append(char)
        return ''.join(chars)

    def handleInfoSocket(self, host, port):
        self.infoSocket = socket.socket()
        try:
            self.infoSocket.connect((host, port))
            while True:
                line = self.readLine(self.infoSocket)
                if not line:
                    break
                self.handleInfoString(line)
        except Exception as exn:
            print("Info socket exception:", exn)
        finally:
            print("handleInfoSocket: Socket connection terminated")

    def handleInfoString(self, infoString):
        parts = infoString.split()
        match parts:
            case [':OK', commandId, *rest]:
                command = Server.ASYNC_COMMANDS_STARTED[commandId]
                if command:
                    command.isComplete = True
                    command.notify()
                    # TODO synchronize access to ASYNC_COMMANDS
                    with Server.ASYNC_COMMANDS_STARTED_LOCK:
                        del Server.ASYNC_COMMANDS_STARTED[commandId]
                else:
                    print("handleInfoString did not find command", commandId)
            case [':DEVICE', where, who, what, *rest]:
                self.handleDevice(where, who, what)
            case [':PAYLOAD', where, who, what, *rest]:
                self.handlePayload(where, who, what)
            case _:
                print("handleInfoString unhandled command", parts)

    def handleDevice(self, where, who, what):
        print("handleDevice", where, who, what)

    def handlePayload(self, where, who, what):
        print("handlePayload", where, who, what)

    def sendCommand(self, command):
        cmdBytes = str.encode(command.string + '\n')
        self.cmdSocket.send(cmdBytes)
        replyBytes = self.cmdSocket.recv(1024)
        replyString = replyBytes.decode('utf-8')
        if replyString.startswith(':OK'):
            command.isStarted = True
            command.isComplete = True
        elif replyString.startswith(':STARTED'):
            replyStringParts = replyString.split()
            commandNumber = replyStringParts[1]
            with Server.ASYNC_COMMANDS_STARTED_LOCK:
                Server.ASYNC_COMMANDS_STARTED[commandNumber] = command
            command.isStarted = True
        elif replyString.startswith(':ERROR'):
            command.isError = True
        elif replyString.startsWith(':OFF'):
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
    server = Server(SERVER, PORT)
    if not server.cmdSocketIsConnected:
        print("Unable to connect to server at", SERVER, PORT);
        exit(1)
    setup(server)
    time.sleep(1)
    runProgram(server)

if __name__ == '__main__':
    main(sys.argv)
