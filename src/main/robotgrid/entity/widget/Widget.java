package robotgrid.entity.widget;

import java.util.Optional;

import robotgrid.entity.Entity;
import robotgrid.shape.Shape;

public abstract class Widget extends Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Widget(final String name, final Shape shape) {
        super(name);
        setView(new _View(this, shape));
    }

    // Instance methods =======================================================

    @Override
    public boolean addPayload(final Entity payload) {
        return false;
    }

    @Override
    public Optional<Entity> removePayload() {
        return Optional.of(this);
    }

}
