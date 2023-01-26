package robotgrid.entity.active.robot;

import robotgrid.entity.active.controller.ICommand;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.entity.active.controller.Controller;

public class ArmController extends Controller {

    // Static inner classes ===================================================

     protected static class ArmExtendCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return _afterDelay(1000, () -> arm.extend());
        }
    }

    protected static class ArmRetractCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return _afterDelay(1000, () -> arm.retract());
        }
    }

    protected static class GripperGripCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return _afterDelay(1000, () -> arm.grip());
        }
    }

    protected static class GripperReleaseCommand implements ICommand {
        @Override
        public CommandResult execute(final Controller controller, final String[] parts) {
            Arm arm = (Arm)controller.entity();
            return _afterDelay(1000, () -> arm.release());
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ArmController() {
        installCommand("ArmExtend", new ArmExtendCommand());
        installCommand("ArmRetract", new ArmRetractCommand());
        installCommand("GripperGrip", new GripperGripCommand());
        installCommand("GripperRelease", new GripperReleaseCommand());
    }

    // Instance methods =======================================================

}
