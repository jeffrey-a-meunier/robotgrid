package robotgrid.server.commands.articulatedrobot;

import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Result;

public class PowerOn extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final ArticulatedRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOn(final ArticulatedRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command command) {
        _robot.powerOn();
        return new Result.Success<Void, String>();
    }

}
