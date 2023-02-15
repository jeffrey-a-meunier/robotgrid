package robotgrid.device.table;

import robotgrid.device.device.Device;

public class Table extends Device {

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
        _maxPayload = 9;
    }

    @Override
    public String typeName() {
        return "Table";
    }

}
