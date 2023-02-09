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
        _cell.grid().move(this, _heading);
        command.ioContext.infoSuccess(command);
    }

    public void moveBackward(final Command command) {
        delay();
        _cell.grid().move(this, _heading.opposite());
        command.ioContext.infoSuccess(command);

    }

    public void rotateLeft(final Command command) {
        // TODO
        command.setErrorMessage( "MovingBase.rotateLeft is not implemented");
    }

    public void rotateRight(final Command command) {
        // TODO
        command.setErrorMessage("MovingBase.rotateLeft is not implemented");
    }

}
