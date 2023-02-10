package robotgrid.entity.movingBase;

import robotgrid.entity.Command;
import robotgrid.entity.PoweredEntity;

public class MovingBase extends PoweredEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public MovingBase(final String name) {
        super(name, 1);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    public void moveForward(final Command command) {
        delay();
        cell().grid().move(this, _heading);
    }

    public void moveBackward(final Command command) {
        delay();
        cell().grid().move(this, _heading.opposite());
    }

    @Override
    public String typeName() {
        return "MovingBase";
    }

}
