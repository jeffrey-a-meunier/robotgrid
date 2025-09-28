#!env python3

import device
import server as srv
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

PROGRAM = [
    ("D1 PowerOn", srv.IMMED),
    ("D1 MoveEast", srv.ASYNC),
    ("D1 PickUp", srv.ASYNC),
    ("D1 MoveEast", srv.ASYNC),
    ("D1 Drop", srv.ASYNC),
    ("D1 MoveWest", srv.ASYNC),
    ("D1 PowerOff", srv.IMMED),
    ("Arm1 Extend", srv.ASYNC),
    ("Arm1 Grip", srv.ASYNC),
    ("Arm1 Retract", srv.ASYNC),
    ("Base1 RotateLeft", srv.ASYNC),
    ("Base1 RotateLeft", srv.ASYNC),
    ("Arm1 Extend", srv.ASYNC),
    ("Arm1 Release", srv.ASYNC),
    ("Arm1 Retract", srv.ASYNC),
    ("Base1 RotateRight", srv.ASYNC),
    ("Base1 RotateRight", srv.ASYNC),
    ("R1 MoveForward", srv.ASYNC),
    ("R1 MoveForward", srv.ASYNC),
    ("R1 MoveForward", srv.ASYNC),
    ("Arm2 Extend", srv.ASYNC),
    ("Arm2 Grip", srv.ASYNC),
    ("Arm2 Retract", srv.ASYNC),
    ("R1 RotateLeft", srv.ASYNC),
    ("R1 RotateLeft", srv.ASYNC),
    ("R1 MoveForward", srv.ASYNC),
    ("R1 MoveForward", srv.ASYNC),
    ("R1 MoveForward", srv.ASYNC),
    ("R1 RotateRight", srv.ASYNC),
    ("R1 RotateRight", srv.ASYNC),
    ("Base2 RotateRight", srv.ASYNC),
    ("Base2 RotateRight", srv.ASYNC),
    ("Arm2 Extend", srv.ASYNC),
    ("Arm2 Release", srv.ASYNC),
    ("Arm2 Retract", srv.ASYNC),
    ("Base2 RotateLeft", srv.ASYNC),
    ("Base2 RotateLeft", srv.ASYNC),
    ("ConveyorGroup1 PowerOn", srv.IMMED)
]

def setup(server):
    for cmdString in SETUP:
        cmd = srv.Command(cmdString)
        print("SETUP:", cmd)
        server.sendCommand(cmd)

# This function runs each command synchronously
def runProgram(server):
    for (cmdString, synchrony) in PROGRAM:
        cmd = srv.Command(cmdString)
        synchrony.apply(cmd)
        # print("PROGRAM:", cmd)
        server.sendCommand(cmd)
        cmd.wait()

def main():
    server = srv.Server(SERVER, PORT)
    if not server.cmdSocketIsConnected:
        print("Unable to connect to server at", SERVER, PORT);
        exit(1)
    setup(server)
#    time.sleep(1)
    runProgram(server)

if __name__ == '__main__':
    main()
