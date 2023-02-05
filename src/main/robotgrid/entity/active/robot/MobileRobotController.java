package robotgrid.entity.active.robot;

import robotgrid.utils.Result;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;

public class MobileRobotController extends Controller {

    // Static inner classes ===================================================

    protected class MoveForwardCommand extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.moveForward();
        }
    }
    
    protected class MoveBackwardCommand extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.moveBackward();
        }
    }

    protected class RotateRightCommand extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] args) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.rotateRight();
        }
    }

    protected class RotateLeftCommand extends CommandHandler {
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
        // installCommand("MoveForward", new MoveForwardCommand());
        // installCommand("MoveBackward", new MoveBackwardCommand());
        // installCommand("RotateRight", new RotateRightCommand());
        // installCommand("RotateLeft", new RotateLeftCommand());
    }

    // Instance methods =======================================================

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + name + '}';
    }

}
