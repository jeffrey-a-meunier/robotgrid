package robotgrid.entity.active.robot;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.utils.Result;

public class ArmController extends Controller {

    // Static inner classes ===================================================

     protected static class ArmExtendCommand extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            Arm arm = (Arm)controller.entity();
            return arm.extend();
        }
    }

    protected static class ArmRetractCommand extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            Arm arm = (Arm)controller.entity();
            return arm.retract();
        }
    }

    protected static class GripperGripCommand extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            Arm arm = (Arm)controller.entity();
            return arm.grip();
        }
    }

    protected static class GripperReleaseCommand extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
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
        // installCommand("ArmExtend", new ArmExtendCommand());
        // installCommand("ArmRetract", new ArmRetractCommand());
        // installCommand("GripperGrip", new GripperGripCommand());
        // installCommand("GripperRelease", new GripperReleaseCommand());
    }

    // Instance methods =======================================================

}
