package robotgrid.entity2.conveyor;

import robotgrid.entity2.Entity2;

public class Conveyor extends Entity2 {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Conveyor(final String name) {
        super(name);
        setView(new _View(this));
        _Commands.setup(this);
    }

}
