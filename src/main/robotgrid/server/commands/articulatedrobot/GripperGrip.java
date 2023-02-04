package robotgrid.server.commands.articulatedrobot;

import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Result;

public class GripperGrip implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final ArticulatedRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public GripperGrip(final ArticulatedRobot robot) {
        _robot = robot;
    }

    // Instance methods =======================================================t

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        return _robot.gripperGrip();
    }

}
