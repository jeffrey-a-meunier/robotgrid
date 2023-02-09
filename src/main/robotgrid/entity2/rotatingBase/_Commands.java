package robotgrid.entity2.rotatingBase;

import robotgrid.entity2.Command;
import robotgrid.entity2.CommandHandler;

class _Commands {

    // Static inner classes ===================================================

    protected static class _RotateLeft extends CommandHandler {
        @Override
        public void execute(final Command command) {
            RotatingBase base = (RotatingBase)command.entity();
            base.rotateLeft(command);
        }
    }

    protected static class _RotateRight extends CommandHandler {
        @Override
        public void execute(final Command command) {
            RotatingBase base = (RotatingBase)command.entity();
            base.rotateRight(command);
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final RotatingBase movingBase) {
        movingBase.addCommandHandler("RotateLeft", new _RotateLeft());
        movingBase.addCommandHandler("RotateRight", new _RotateRight());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
