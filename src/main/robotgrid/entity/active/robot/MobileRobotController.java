package robotgrid.entity.active.robot;

import robotgrid.entity.active.controller.ICommand;
import robotgrid.utils.Result;
import robotgrid.entity.active.controller.Controller;

public class MobileRobotController extends Controller {

    // Static inner classes ===================================================

    protected class MoveForwardCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.moveForward();
        }
    }
    
    protected class MoveBackwardCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.moveBackward();
        }
    }

    protected class RotateRightCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            MobileRobot robot = (MobileRobot)controller.entity();
            return robot.rotateRight();
        }
    }

    protected class RotateLeftCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
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
        installCommand("MoveForward", new MoveForwardCommand());
        installCommand("MoveBackward", new MoveBackwardCommand());
        installCommand("RotateRight", new RotateRightCommand());
        installCommand("RotateLeft", new RotateLeftCommand());
    }

    // Instance methods =======================================================

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + _name + '}';
    }

}
