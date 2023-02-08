package robotgrid.entity2.movingBase;

import robotgrid.entity2.Command;
import robotgrid.entity2.CommandHandler;

class _CommandHandlers {

    // Static inner classes ===================================================

    protected static class _MoveForward extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.entity();
            base.moveForward(command);
        }
    }

    protected static class _MoveBackward extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.entity();
            base.moveBackward(command);
        }
    }

    protected static class _RotateLeft extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.entity();
            base.rotateLeft(command);
        }
    }

    protected static class _RotateRight extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.entity();
            base.rotateRight(command);
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final MovingBase movingBase) {
        movingBase.addCommandHandler("RotateLeft", new _RotateLeft());
        movingBase.addCommandHandler("RotateRight", new _RotateRight());
        movingBase.addCommandHandler("MoveForward", new _MoveForward());
        movingBase.addCommandHandler("MoveBackward", new _MoveBackward());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
