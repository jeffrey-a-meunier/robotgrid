package robotgrid.entity.rotatingBase;

import robotgrid.entity.Entity;
import robotgrid.entity.PoweredEntity;

public class RotatingBase extends PoweredEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public RotatingBase(final String name) {
        super(name, 1);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    @Override
    public void rotateLeft() {
        delay();
        Entity payload = payload();
        if (payload != null) {
            payload.rotateLeft_immediate();
        }
    }

    @Override
    public void rotateRight() {
        delay();
        Entity payload = payload();
        if (payload != null) {
            payload.rotateRight_immediate();
        }
    }

    @Override
    public String toString() {
        return "RotatingBase{" + name + '}';
    }

}
