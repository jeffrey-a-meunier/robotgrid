package robotgrid.server.commands.articulatedrobot;

import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class Power extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final ArticulatedRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Power(final ArticulatedRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command command) {
        Server.THE_SERVER.sendCommandReply(_robot.isOn() ? "ON" : "OFF");
        return new Result.Success<Void, String>();
    }

}
