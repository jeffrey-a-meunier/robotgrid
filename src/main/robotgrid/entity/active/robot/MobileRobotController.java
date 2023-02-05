package robotgrid.entity.active.robot;

import robotgrid.utils.Result;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;

public class MobileRobotController extends Controller {

    // Static inner classes ===================================================

    protected class MoveForward extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.moveForward();
        }
    }
    
    protected class MoveBackward extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.moveBackward();
        }
    }

    protected class RotateRight extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.rotateRight();
        }
    }

    protected class RotateLeft extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.rotateLeft();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public MobileRobotController(final String name) {
        super(name);
        addCommandHandler("RotateLeft", new RotateLeft());
        addCommandHandler("RotateRight", new RotateRight());
        addCommandHandler("MoveForward", new MoveForward());
        addCommandHandler("MoveBackward", new MoveBackward());
    }

    // Instance methods =======================================================

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + name + '}';
    }

}
