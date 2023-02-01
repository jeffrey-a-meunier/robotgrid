package robotgrid.entity.active;

import robotgrid.entity.Entity;
import robotgrid.entity.active.controller.Controller;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public abstract class ActiveEntity extends Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================

    /**
     * Any device that does not override the deviceLatency() method will use this
     * value for its latency.
     */
    protected static float _STANDARD_LATENCY = 1000.0f;

    private static Logger _logger = new Logger(ActiveEntity.class, Logger.Level.Debug);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Controller _controller;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ActiveEntity(final String name) {
        this(name, new Controller(name));
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

    /**
     * This method is used by subclasses to simulate real-world latency of motion.
     * I guess that's called inertia.
     */
    public void delay() {
        long delay = (long)(deviceLatency() * World.SIMULATION_SPEED);
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException exn) {
            _logger.warn("delay(", delay, "): thread interrupted for object " + this);
        }
    }

    public float deviceLatency() {
        return _STANDARD_LATENCY;
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
