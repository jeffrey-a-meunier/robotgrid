package robotgrid.entity.abstractEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public abstract class AbstractEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================

    /**
     * Any device that does not override the deviceLatency() method will use this
     * value for its latency.
     */
    protected static float _STANDARD_LATENCY = 1000.0f;  // milliseconds

    protected static final Map<String, AbstractEntity> _ALL_ENTITIES = new HashMap<>();

    private Logger _LOGGER = new Logger(AbstractEntity.class);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static AbstractEntity lookup(final String name) {
        return _ALL_ENTITIES.get(name);
    }

    public static List<String> names() {
        Set<String> nameSet = _ALL_ENTITIES.keySet();
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

    public AbstractEntity(final String name) {
        this.name = name;
        _Commands.setup(this);
        _ALL_ENTITIES.put(name, this);
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
     * Only a PoweredEntity can have its power switched on or off, but putting
     * this method in this class simplifies command handling in the Server class.
     * The server does not deliver command messages to an entity if it is not on.
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
