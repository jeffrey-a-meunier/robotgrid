#!/usr/local/bin/python3

import socket
import sys
import threading
import time

SERVER = '127.0.0.1'
PORT = 43210

SETUP = [
    "World CreateDrone D1 2 1",
    "D1 PowerOn",
    "World CreateTable T1 2 2",
    "World CreateWidget W1 2 2"
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
        replyType = parts[0]
        if replyType == ':OK':
            commandId = parts[1]
            command = Server.ASYNC_COMMANDS_STARTED[commandId]
            if command:
                command.isComplete = True
                command.notify()
                # TODO synchronize access to ASYNC_COMMANDS
                del Server.ASYNC_COMMANDS_STARTED[commandId]
            else:
                print("handleInfoString did not find command", commandId)
        else:
            print("handleInfoString unhandled reply type '", replyType, "'", sep='');

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
