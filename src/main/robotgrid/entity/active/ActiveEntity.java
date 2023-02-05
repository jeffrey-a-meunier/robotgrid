package robotgrid.entity.active;

import robotgrid.entity.Entity;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;

public abstract class ActiveEntity extends Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Controller _controller;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ActiveEntity(final String name, final Controller controller) {
        super(name);
        _controller = controller;
        _controller.setEntity(this);
    }

    public ActiveEntity setController(final Controller controller) {
        _controller = controller;
        return this;
    }

    // Instance methods =======================================================

    public Controller controller() {
        return _controller;
    }

    public boolean isOn() {
        return _controller.isOn();
    }

    public void powerOn() {
        _controller.powerOn();
    }

    public void powerOff() {
        _controller.powerOff();
    }

    public boolean sendCommand(final CommandHandler command) {
        return _controller.sendCommand(command);
    }

}
