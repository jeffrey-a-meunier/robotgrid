package robotgrid.server.commands.articulatedrobot;

import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Result;

public class PowerOff extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final ArticulatedRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOff(final ArticulatedRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command_deprecated command) {
        _robot.powerOff();
        return new Result.Success<Void, String>();
    }

}
