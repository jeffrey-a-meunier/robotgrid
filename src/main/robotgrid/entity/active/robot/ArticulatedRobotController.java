package robotgrid.entity.active.robot;

import robotgrid.utils.Result;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;

public class ArticulatedRobotController extends Controller {

    // Static inner classes ===================================================

    protected static class RotateRight extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.rotateRight();
        }
    }

    protected static class RotateLeft extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.rotateLeft();
        }
    }

    protected static class ArmExtend extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.armExtend();
        }
    }

    protected static class ArmRetract extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.armRetract();
        }
    }

    protected static class GripperGrip extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            ArticulatedRobot robot = (ArticulatedRobot)controller.entity();
            return robot.gripperGrip();
        }
    }

    protected static class GripperRelease extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
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
        addCommandHandler("RotateRight", new RotateRight());
        addCommandHandler("RotateLeft", new RotateLeft());
        addCommandHandler("Extend", new ArmExtend());
        addCommandHandler("Retract", new ArmRetract());
        addCommandHandler("Grip", new GripperGrip());
        addCommandHandler("Release", new GripperRelease());
    }

    // Instance methods =======================================================

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + name + '}';
    }

}
