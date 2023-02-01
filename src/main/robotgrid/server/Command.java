package robotgrid.server;

public class Command {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_COMMAND_INDEX = 0;
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _commandIndex = _NEXT_COMMAND_INDEX++;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String string) {
    }

    // Instance methods =======================================================

    final void execute() {
    }

}
