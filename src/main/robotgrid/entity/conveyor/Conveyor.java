package robotgrid.entity.conveyor;

import robotgrid.entity.Entity;

public class Conveyor extends Entity {

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

    @Override
    public String typeName() {
        return "Conveyor";
    }

}
