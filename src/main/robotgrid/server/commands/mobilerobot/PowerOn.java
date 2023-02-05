package robotgrid.server.commands.mobilerobot;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Result;

public class PowerOn extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected MobileRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOn(final MobileRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command_deprecated command) {
        _robot.powerOn();
        return new Result.Success<Void, String>();
    }

}
