package robotgrid.entity;

import java.util.List;

import robotgrid.utils.Logger;
import robotgrid.utils.SynQ;
import robotgrid.world.World;

public abstract class PoweredEntity extends Entity implements Runnable {

    // Static inner classes ===================================================
    // Static variables =======================================================

    /**
     * Any device that does not override the deviceLatency() method will use this
     * value for its latency.
     */
    protected static float _STANDARD_LATENCY = 1000.0f;  // milliseconds

    private Logger _LOGGER = new Logger(PoweredEntity.class, Logger.Level.All);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected boolean _isOn = false;
    protected SynQ<Command> _commandQ = new SynQ<>();
    protected Thread _thread;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PoweredEntity(final String name, final int height) {
        super(name, height);
        PoweredEntity_Commands.setup(this);
    }

    // Instance methods =======================================================

    /**
     * This method is used by subclasses to simulate real-world latency of motion,
     * otherwise known as inertia.
     */
    public void delay() {
        System.out.println("PoweredEntity.delay() called on entity " + this);
        long delay = (long)(deviceLatency() / World.SIMULATION_SPEED);
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException exn) {
            _LOGGER.warn("delay(", delay, "): thread interrupted for entity " + this);
        }
    }

    public float deviceLatency() {
        return _STANDARD_LATENCY;
    }

    public void info(final List<String> infoStrings) {
        super.info(infoStrings);
        infoStrings.add("PoweredOn=" + (_isOn ? "True" : "False"));
    }

    public boolean isOn() {
        return _isOn;
    }

    public void powerOn() {
        _thread = new Thread(this);
        _thread.start();
        _isOn = true;
    }

    public void powerOff() {
        _isOn = false;
        _thread.interrupt();
        _commandQ.clear();
    }

    @Override
    public void run() {
        while (!_thread.isInterrupted()) {
            Command command = _commandQ.deq();
            if (command == null) {
                // a null command means that the thread has been interrupted
                break;
            }
            command.execute();
        }
    }

    @Override
    public void sendCommand(final Command command) {
        _commandQ.enq(command);
    }

}
