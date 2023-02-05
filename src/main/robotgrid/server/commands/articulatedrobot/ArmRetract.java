package robotgrid.server.commands.articulatedrobot;

import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Result;

public class ArmRetract extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final ArticulatedRobot _robot;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ArmRetract(final ArticulatedRobot robot, final String ... commandParts) {
        super(commandParts);
        _robot = robot;
    }

    // Instance methods =======================================================t

    @Override
    public Result<Void, String> handleCommand(final Command_deprecated command) {
        return _robot.armRetract();
    }

}
