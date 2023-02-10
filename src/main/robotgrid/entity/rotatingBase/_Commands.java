package robotgrid.entity.rotatingBase;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;

class _Commands {

    // Static inner classes ===================================================

    protected static class _RotateLeft extends CommandHandler {
        @Override
        public void execute(final Command command) {
            RotatingBase base = (RotatingBase)command.entity();
            base.rotateLeft();
        }
    }

    protected static class _RotateRight extends CommandHandler {
        @Override
        public void execute(final Command command) {
            RotatingBase base = (RotatingBase)command.entity();
            base.rotateRight();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final RotatingBase base) {
        base.addCommandHandler("RotateLeft", new _RotateLeft());
        base.addCommandHandler("RotateRight", new _RotateRight());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
