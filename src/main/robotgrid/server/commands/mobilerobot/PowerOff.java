package robotgrid.server.commands.mobilerobot;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Result;

public class PowerOff extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected MobileRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOff(final MobileRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command command) {
        _robot.powerOff();
        return new Result.Success<Void, String>();
    }

}
