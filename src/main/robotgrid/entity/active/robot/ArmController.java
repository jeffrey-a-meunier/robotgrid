package robotgrid.entity.active.robot;

import robotgrid.entity.active.controller.ICommand;
import robotgrid.utils.Result;
import robotgrid.entity.active.controller.Controller;

public class ArmController extends Controller {

    // Static inner classes ===================================================

     protected static class ArmExtendCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return arm.extend();
        }
    }

    protected static class ArmRetractCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return arm.retract();
        }
    }

    protected static class GripperGripCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return arm.grip();
        }
    }

    protected static class GripperReleaseCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return arm.release();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ArmController(final String name) {
        super(name);
        installCommand("ArmExtend", new ArmExtendCommand());
        installCommand("ArmRetract", new ArmRetractCommand());
        installCommand("GripperGrip", new GripperGripCommand());
        installCommand("GripperRelease", new GripperReleaseCommand());
    }

    // Instance methods =======================================================

}
