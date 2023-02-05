package robotgrid.server.commands.mobilerobot;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Result;

public class MoveForward extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final MobileRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public MoveForward(final MobileRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================t

    @Override
    public Result<Void, String> handleCommand(final Command_deprecated command) {
        return _robot.moveForward();
    }

}
