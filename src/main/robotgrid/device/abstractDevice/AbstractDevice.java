package robotgrid.device.abstractDevice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public abstract class AbstractDevice {

    // Static inner classes ===================================================
    // Static variables =======================================================

    /**
     * Any device that does not override the deviceLatency() method will use this
     * value for its latency.
     */
    protected static float _STANDARD_LATENCY = 1000.0f;  // milliseconds

    protected static final Map<String, AbstractDevice> _ALL_DEVICES = new HashMap<>();

    private Logger _LOGGER = new Logger(AbstractDevice.class);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static AbstractDevice lookup(final String name) {
        return _ALL_DEVICES.get(name);
    }

    public static List<String> names() {
        Set<String> nameSet = _ALL_DEVICES.keySet();
        List<String> nameList = new ArrayList<>(nameSet);
        Collections.sort(nameList);
        return nameList;
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String name;

    protected Map<String, CommandHandler> _commandHandlers = new HashMap<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public AbstractDevice(final String name) {
        this.name = name;
        _Commands.setup(this);
        _ALL_DEVICES.put(name, this);
    }

    // Instance methods =======================================================

    public void addCommandHandler(final String name, final CommandHandler handler) {
        _commandHandlers.put(name, handler);
    }

    public void listCommands(final List<String> strings) {
        Set<String> nameSet = _commandHandlers.keySet();
        List<String> nameList = new ArrayList<>(nameSet);
        Collections.sort(nameList);
        for (String name : nameList) {
            strings.add(name);
        }
    }

    public CommandHandler locateCommandHandler(final String name) {
        return _commandHandlers.get(name);
    }

    public void sendCommand(final Command command) {
        command.execute();
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
        catch (final InterruptedException exn) {
            _LOGGER.warn("delay(", delay, "): thread interrupted for object " + this);
        }
    }


    public float deviceLatency() {
        return _STANDARD_LATENCY;
    }

    public void info(final List<String> strings) {
        strings.add("Type=" + getClass().getSimpleName());
    }

     /**
     * Only a PoweredDevice can have its power switched on or off, but putting
     * this method in this class simplifies command handling in the Server class.
     * The server does not deliver command messages to a device if it is not on.
     */
    public boolean isOn() {
        return true;
    }

    @Override
    public String toString() {
        return typeName() + '{' + name + '}';
    }

    public abstract String typeName();

}
