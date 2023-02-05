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

    // protected class PowerOn extends CommandHandler {
    //     public PowerOn() { setImmeidate(true); }
    //     @Override
    //     protected Result<Void, String> _execute(final Controller controller, final String[] args) {
    //         MobileRobot robot = (MobileRobot)controller.entity();
    //         robot.powerOn();
    //         return new Result.Success<>();
    //     }
    // }

    // protected class PowerOff extends CommandHandler {
    //     public PowerOff() { setImmeidate(true); }
    //     @Override
    //     protected Result<Void, String> _execute(final Controller controller, final String[] args) {
    //         MobileRobot robot = (MobileRobot)controller.entity();
    //         robot.powerOff();
    //         return new Result.Success<>();
    //     }
    // }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public MobileRobotController(final String name) {
        super(name);
        addCommandHandler("MoveForward", new MoveForward());
        addCommandHandler("MoveBackward", new MoveBackward());
        addCommandHandler("RotateLeft", new RotateLeft());
        addCommandHandler("RotateRight", new RotateRight());
        // addCommandHandler("PowerOn", new PowerOn());
        // addCommandHandler("PowerOff", new PowerOff());
    }

    // Instance methods =======================================================

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + name + '}';
    }

}
