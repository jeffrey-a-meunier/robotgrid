package robotgrid.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public abstract class Entity implements IContainer {

    // Static inner classes ===================================================
    // Static variables =======================================================

    /**
     * Any device that does not override the deviceLatency() method will use this
     * value for its latency.
     */
    protected static float _STANDARD_LATENCY = 1000.0f;  // milliseconds

    protected static final Map<String, Entity> _ALL_ENTITIES = new HashMap<>();

    private Logger _LOGGER = new Logger(Entity.class);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static Entity lookup(final String name) {
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
    public final int height;

    protected View _view;
    protected IContainer _container;
    protected Direction _heading = Direction.North;
    protected Entity _payload;

    protected Map<String, CommandHandler> _commandHandlers = new HashMap<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Entity(final String name) {
        this(name, 0);
    }

    public Entity(final String name, final int height) {
        this.name = name;
        this.height = height;
        Entity_Commands.setup(this);
        _ALL_ENTITIES.put(name, this);
    }

    public Entity setHeading(final Direction heading) {
        _heading = heading;
        if (_view != null) {
            _view.setAngle(_heading.getAngle());
        }
        return this;
    }

    public Entity setView(final View view) {
        _view = view;
        _view.setAngle(_heading.getAngle());
        return this;
    }

    // Instance methods =======================================================

    public void addCommandHandler(final String name, final CommandHandler handler) {
        _commandHandlers.put(name, handler);
    }

    public synchronized boolean addPayload(final Entity payload) {
        if (_payload != null) {
            return false;
        }
        _payload = payload;
        payload.setContainer(this);
        return true;
    }

    public Entity removePayload() {
        Entity entity = _payload;
        _payload = null;
        return entity;
    }

    public Entity payload() {
        return _payload;
    }

    public Cell cell() {
        IContainer container = _container;
        while (true) {
            if (container == null) {
                return null;
            }
            if (container instanceof Cell) {
                return (Cell)container;
            }
            else if (container instanceof Entity) {
                container = ((Entity)container)._container;
            }
        }
    }

    public void setContainer(final IContainer container) {
        _container = container;
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

    public void draw(final Graphics graphics, final int layerNum) {
        _view.draw(graphics, layerNum);
    }

    public void info(final List<String> strings) {
        strings.add("Type=" + getClass().getSimpleName());
        strings.add("Heading=" + _heading);
        strings.add("Payload=" + ((_payload == null) ? "No" : "Yes"));
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

    public void rotateLeft() {
        delay();
        rotateLeft_immediate();
    }

    public void rotateLeft_immediate() {
        setHeading(_heading.turnLeft());
    }

    public void rotateRight() {
        delay();
        rotateRight_immediate();
    }

    public void rotateRight_immediate() {
        setHeading(_heading.turnRight());
    }


    public void sendCommand(final Command command) {
        command.execute();
    }

}
