package robotgrid.entity2.table;

import robotgrid.entity2.Entity2;

public class Table extends Entity2 {

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
