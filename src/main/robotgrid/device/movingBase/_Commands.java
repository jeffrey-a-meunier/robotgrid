package robotgrid.device.movingBase;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;

class _Commands {

    // Static inner classes ===================================================

    protected static class _MoveForward extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.device();
            base.moveForward(command);
        }
    }

    protected static class _MoveBackward extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.device();
            base.moveBackward(command);
        }
    }

    protected static class _RotateLeft extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.device();
            base.rotateLeft();
        }
    }

    protected static class _RotateRight extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.device();
            base.rotateRight();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final MovingBase base) {
        base.addCommandHandler("RotateLeft", new _RotateLeft());
        base.addCommandHandler("RotateRight", new _RotateRight());
        base.addCommandHandler("MoveForward", new _MoveForward());
        base.addCommandHandler("MoveBackward", new _MoveBackward());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
