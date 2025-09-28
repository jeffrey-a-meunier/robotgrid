import server as srv

class Device:

    def __init__(self, server, type, name):
        self.server = server
        self.type = type
        self.name = name

    def setInitialRow(self, row):
        self.initialRow = row
        return self

    def setInitialCol(self, col):
        self.initialCol = col
        return self

    def create(self, row, col, heading=None):
        commandString = "World Create" + self.type + " " + self.name + " " + str(row) + " " + str(col)
        if heading:
            commandString += " " + self.heading
        command = srv.Command(commandString)
        self.server.sendCommand(command)

    def powerOn(self):
        command = srv.Command(self.name + " PowerOn")
        self.server.sendCommand(command)
        return command

    def powerOff(self):
        command = srv.Command(self.name + " PowerOff")
        self.server.sendCommand(command)
        return command

    def _move(self, direction):
        command = srv.Command(self.name + " Move" + direction)
        self.server.sendCommand(command)
        return command

    def moveEast(self):
        return self._move("East")

    def moveSouth(self):
        return self._move("South")

    def moveWest(self):
        return self._move("West")

    def moveNorth(self):
        return self._move("North")

class Drone (Device):

    def __init__(self, server, name):
        super().__init__(server, "Drone", name)

