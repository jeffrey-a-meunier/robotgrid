package robotgrid.server.commands.mobilerobot;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Result;

public class RotateRight extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final MobileRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public RotateRight(final MobileRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================t

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        return _robot.rotateRight();
    }

}
