package robotgrid.entity.rotatingBase;

import java.util.Optional;

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
        Optional<Entity> payload_opt = peekPayload();
        if (payload_opt.isPresent()) {
            Entity payload = payload_opt.get();
            payload.rotateLeft_immediate();
        }
    }

    @Override
    public void rotateRight() {
        delay();
        Optional<Entity> payload_opt = peekPayload();
        if (payload_opt.isPresent()) {
            Entity payload = payload_opt.get();
            payload.rotateRight_immediate();
        }
    }

    @Override
    public String typeName() {
        return "RotatingBase";
    }

}
