package robotgrid.entity.conveyor;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;

class _Commands {

    // Static inner classes ===================================================

    protected static class _Reverse extends CommandHandler {
        @Override
        public void execute(final Command command) {
            Conveyor conveyor = (Conveyor)command.entity();
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
