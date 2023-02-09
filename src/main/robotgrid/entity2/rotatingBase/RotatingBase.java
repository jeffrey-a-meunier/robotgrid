package robotgrid.entity2.rotatingBase;

import robotgrid.entity2.Command;
import robotgrid.entity2.PoweredEntity;

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

    public void rotateLeft(final Command command) {
        // TODO
        command.setErrorMessage( "RotatingBase.rotateLeft is not implemented");
    }

    public void rotateRight(final Command command) {
        // TODO
        command.setErrorMessage("RotatingBase.rotateLeft is not implemented");
    }

}
