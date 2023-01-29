package robotgrid.entity.active.robot;

import robotgrid.entity.active.controller.ICommand;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.entity.active.controller.Controller;

public class MobileRobotController extends Controller {

    // Static inner classes ===================================================

    protected class MoveForwardCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            System.out.println("MobileRobotController moving forward");
            MobileRobot robot = (MobileRobot)controller.entity();
            return _afterDelay(1000, () -> robot.moveForward());
        }
    }
    
    protected class MoveBackwardCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            System.out.println("MobileRobotController moving backward");
            MobileRobot robot = (MobileRobot)controller.entity();
            return _afterDelay(1000, () -> robot.moveBackward());
        }
    }

    protected class RotateRightCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            System.out.println("MobileRobotController rotating right");
            MobileRobot robot = (MobileRobot)controller.entity();
            return _afterDelay(1000, () -> robot.rotateRight());
        }
    }

    protected class RotateLeftCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            System.out.println("MobileRobotController rotating left");
            MobileRobot robot = (MobileRobot)controller.entity();
            return _afterDelay(1000, () -> robot.rotateLeft());
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
        return "MobileRobotController{" + _entity + '}';
    }

}
