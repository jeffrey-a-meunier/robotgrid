package robotgrid.entity2.widget;

import robotgrid.entity2.Entity2;
import robotgrid.entity2.View;

/**
 * By subclassing Entity, a Widget will have a payload property. This
 * will be ignored. A Widget can only *be* a payload.
 */
public class Widget extends Entity2 {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Widget(String name, View view) {
        super(name, 0);
        _view = new _View(this);
    }

    // Instance methods =======================================================

    @Override
    public boolean addPayload(final Entity2 payload) {
        return false;
    }

    @Override
    public Entity2 removePayload() {
        return this;
    }

}
