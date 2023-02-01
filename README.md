# RobotGrid

The server listens on two sockets, a command socoket on port 43210 and an info
socket on port 43211.

Robot commands can be sent to the command scoket, and information regarding
the system's status can be read from the info socket.

Typical usage:

1. Start RobotGrid and wait for the Server to start running:
```
[INFO ] 2023-02-01 04:50:10 EST |Server| Command handler listening on port 43210
[INFO ] 2023-02-01 04:50:10 EST |Server| Info handler listening on port 43211
[INFO ] 2023-02-01 04:50:10 EST |World| Server created: robotgrid.server.Server@2ef1e4fa
```

2. Open a terminal window and connect to the info socket:
```
$ nc localhost 43211
```

3. Open another terminal window and connect to the command socket:
```
$ nc localhost 43210
```

4. Send commands over the command socket:
    ArticulatedRobot1 ArmExtend
