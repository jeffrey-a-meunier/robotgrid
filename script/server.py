#!/usr/local/bin/python3

import socket
import sys
import threading
import time

SERVER = '127.0.0.1'
PORT = 43210

# SETUP = [
#     "World CreateMovingBase R1 3 2 East",
#     "R1 PowerOn",
#     "World CreateRotatingBase Base1 4 2",
#     "World CreateArm Arm1 4 2 South",
#     "Base1 PowerOn",
#     "Arm1 PowerOn",
#     "World CreateRotatingBase Base2 4 5",
#     "World CreateArm Arm2 4 5 North",
#     "Base2 PowerOn",
#     "Arm2 PowerOn",
#     "World CreateConveyor Conveyor1 5 5 West",
#     "World CreateConveyor Conveyor2 5 4 West",
#     "World CreateConveyor Conveyor3 5 3 West",
#     "World CreateGroup ConveyorGroup1",
#     "ConveyorGroup1 Add Conveyor1 Conveyor2 Conveyor3",
#     "World CreateTable Table1 5 2",
#     "World CreateDrone D1 5 0",
#     "World CreateWidget Widget3 5 1"
# ]

class Immediate:
    def apply(self, cmd):
        cmd.isAsync = False

class Asynchronous:
    def apply(self, cmd):
        cmd.isAsync = True

IMMED = Immediate()
ASYNC = Asynchronous()

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
    AIR_CONTENT = {}
    AIR_SUBSCRIBERS = {}
    GROUND_CONTENT = {}
    GROUND_SUBSCRIBERS = {}

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
            case [':ADDED', where, who, what, *rest]:
                self.handleContentAdded(where, who, what)
            case [':REMOVED', where, who, what, *rest]:
                self.handleContentRemoved(where, who, what)
            case _:
                print("handleInfoString unhandled command", parts)

    def handleContentAdded(self, where, who, what):
        print("handleContentAdded", where, who, what)

    def handleContentRemoved(self, where, who, what):
        print("handleContentRemoved", where, who, what)

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
