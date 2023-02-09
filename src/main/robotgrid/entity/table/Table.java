package robotgrid.entity.table;

import robotgrid.entity.Entity;

public class Table extends Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _lineColor2 = 0xFF_80_80_80;
    protected float _lineSize = 1.0f;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Table(final String name) {
        super(name);
        setView(new _View(this));
    }

}
