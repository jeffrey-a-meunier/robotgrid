package robotgrid.entity2.movingBase;

import robotgrid.entity2.Command;
import robotgrid.entity2.PoweredEntity;

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
        _view = new _View(this);
        _CommandHandlers.setup(this);
        System.out.println("MovingBase " + name + " created");
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
