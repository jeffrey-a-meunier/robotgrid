package robotgrid.entity.active;

import robotgrid.entity.Entity;
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

    public ActiveEntity(final String name) {
        this(name, new Controller(name + ".Controller"));
    }

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

    public String name() {
        return _name;
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

}
