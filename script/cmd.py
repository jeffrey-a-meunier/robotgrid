#!/usr/local/bin/python3

import sys
import zmq

PORT = 43210

def main(args):
    if len(args) < 3:
        print(f"Use: {args[0]} <controller-name> <action> [<arguments>]")
        exit(1)
    context = zmq.Context()
    socket = context.socket(zmq.PAIR)
    res = socket.connect("tcp://localhost:" + str(PORT))
    message = ' '.join(args[1:])
    res = socket.send_string(message)

if __name__ == '__main__':
    main(sys.argv)
