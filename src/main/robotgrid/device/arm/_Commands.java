package robotgrid.device.arm;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;

class _Commands {

    // Static inner classes ===================================================

    protected static class ArmExtendCommand extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Arm arm = (Arm)command.device();
            arm.extend();
        }
    }

    protected static class ArmRetractCommand extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Arm arm = (Arm)command.device();
            arm.retract();
        }
    }

    protected static class GripperGripCommand extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Arm arm = (Arm)command.device();
            arm.grip();
        }
    }

    protected static class GripperReleaseCommand extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Arm arm = (Arm)command.device();
            arm.release();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public static void setup(final Arm arm) {
        arm.addCommandHandler("Extend", new ArmExtendCommand());
        arm.addCommandHandler("Retract", new ArmRetractCommand());
        arm.addCommandHandler("Grip", new GripperGripCommand());
        arm.addCommandHandler("Release", new GripperReleaseCommand());
    }

}
