package robotgrid.entity.active.robot;

import robotgrid.utils.Result;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;

public class ArticulatedRobotController extends Controller {

    // Static inner classes ===================================================

    protected static class RotateRightCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.rotateRight();
        }
    }

    protected static class RotateLeftCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.rotateLeft();
        }
    }

    protected static class ArmExtendCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.armExtend();
        }
    }

    protected static class ArmRetractCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.armRetract();
        }
    }

    protected static class GripperGripCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.gripperGrip();
        }
    }

    protected static class GripperReleaseCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
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
        // installCommand("RotateRight", new RotateRightCommand());
        // installCommand("RotateLeft", new RotateLeftCommand());
        // installCommand("ArmExtend", new ArmExtendCommand());
        // installCommand("ArmRetract", new ArmRetractCommand());
        // installCommand("GripperGrip", new GripperGripCommand());
        // installCommand("GripperRelease", new GripperReleaseCommand());
    }

    // Instance methods =======================================================

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + name + '}';
    }

}
