package robotgrid.entity.movingBase;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;

class _Commands {

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
            System.out.println("Entity._RotateLeft.execute called");
            base.rotateLeft();
        }
    }

    protected static class _RotateRight extends CommandHandler {
        @Override
        public void execute(final Command command) {
            MovingBase base = (MovingBase)command.entity();
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
