package robotgrid.device.conveyor;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;

class _Commands {

    // Static inner classes ===================================================

    protected static class _Reverse extends CommandHandler {
        public _Reverse() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Conveyor conveyor = (Conveyor)command.device();
            conveyor.reverse();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Conveyor conveyor) {
        conveyor.addCommandHandler("Reverse", new _Reverse());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
