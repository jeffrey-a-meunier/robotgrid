#!env python3

import time

import device
import server as srv

SERVER = '127.0.0.1'
PORT = 43210

def run1(server):
    drone1 = device.Drone(server, "Drone1")
    drone1.create(4, 4)
    time.sleep(1)
    drone1.powerOn()
    drone1.moveEast()
    drone1.moveSouth()
    # This is buggy:
    drone1.moveWest()
    drone1.moveNorth()
    time.sleep(1)
    drone1.powerOff()

def run(server):
    drone1 = device.Drone(server, "Drone1")
    drone1.create(4, 8)
    time.sleep(1)
    drone1.powerOn()
    drone1.moveWest()
    print("added command 1")
    drone1.moveWest()
    print("added command 2")
    drone1.moveWest()
    print("added command 3")
    drone1.moveWest()
    print("added command 4")
    drone1.moveWest()
    print("added command 5")
    cmd = drone1.moveWest()
    print("added command 6")
    cmd.wait()
    print("Command", cmd, "is finished")
    drone1.powerOff()

def main():
    server = srv.Server(SERVER, PORT)
    if not server.cmdSocketIsConnected:
        print("Unable to connect to server at", SERVER, PORT);
        exit(1)
    run(server)

if __name__ == '__main__':
    main()
