package robotgrid.utils;

/**
 * Unique identifier for commands.
 */
public class UID {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 0;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final int id = _NEXT_ID++;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public UID() {}

    // Instance methods =======================================================

    @Override
    public String toString() {
        return "" + id;
    }

}
