package robotgrid.entity.active.robot;

import robotgrid.entity.active.controller.ICommand;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.entity.active.controller.Controller;

public class ArticulatedRobotController extends Controller {

    // Static inner classes ===================================================

    protected static class RotateRightCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.rotateRight();
        }
    }

    protected static class RotateLeftCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.rotateLeft();
        }
    }

    protected static class ArmExtendCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.armExtend();
        }
    }

    protected static class ArmRetractCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.armRetract();
        }
    }

    protected static class GripperGripCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.gripperGrip();
        }
    }

    protected static class GripperReleaseCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.gripperRelease();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ArticulatedRobotController(final String name) {
        super(name);
        installCommand("RotateRight", new RotateRightCommand());
        installCommand("RotateLeft", new RotateLeftCommand());
        installCommand("ArmExtend", new ArmExtendCommand());
        installCommand("ArmRetract", new ArmRetractCommand());
        installCommand("GripperGrip", new GripperGripCommand());
        installCommand("GripperRelease", new GripperReleaseCommand());
    }

    // Instance methods =======================================================

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + _name + '}';
    }

}
