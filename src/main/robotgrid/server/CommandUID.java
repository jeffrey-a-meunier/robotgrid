package robotgrid.server;

/**
 * Unique identifier for commands.
 */
public class CommandUID {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 0;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _id = _NEXT_ID++;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CommandUID() {}

    // Instance methods =======================================================

    @Override
    public String toString() {
        return "" + _id;
    }

}
