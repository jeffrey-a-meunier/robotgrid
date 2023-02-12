package robotgrid.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.Optional;
import java.util.Set;

import robotgrid.entity.abstractEntity.AbstractEntity;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.server.Client;
import robotgrid.world.World;

public abstract class Entity extends AbstractEntity implements IContainer {

    // Static inner classes ===================================================
    // Static variables =======================================================

    /**
     * Any device that does not override the deviceLatency() method will use this
     * value for its latency.
     */
    protected static float _STANDARD_LATENCY = 1000.0f;  // milliseconds

     // Static initializer =====================================================
    // Static methods =========================================================

    public static List<String> names() {
        Set<String> nameSet = _ALL_ENTITIES.keySet();
        List<String> nameList = new ArrayList<>(nameSet);
        Collections.sort(nameList);
        return nameList;
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _height;
    protected View _view;
    protected IContainer _container;
    protected Direction _heading = Direction.North;
    protected List<Entity> _payload = new ArrayList<>();
    protected int _maxPayload = 1;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Entity(final String name) {
        this(name, 0);
    }

    public Entity(final String name, final int height) {
        super(name);
        this._height = height;
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

    @Override  // from IContainer
    public boolean addPayload(final Entity payload) {
        synchronized (_payload) {
            if (_payload.size() < _maxPayload) {
                _payload.add(payload);
                payload.setContainer(this);
                Client.INFO.payloadNotice(this, payload);
                return true;
            }
            return false;
        }
    }

    @Override  // from IContainer
    public int payloadCount() {
        synchronized (_payload) {
            return _payload.size();
        }
    }

    public Optional<Entity> peekPayload() {
        if (_payload.size() > 0) {
            return Optional.of(_payload.get(0));
        }
        return Optional.empty();
    }

    @Override  // from IContainer
    public Optional<Entity> removePayload() {
        synchronized (_payload) {
            if (_payload.size() > 0) {
                Entity payload = (Entity) _payload.remove(0);
                Client.INFO.payloadNotice(this, null);
                return Optional.of(payload);
            }
            return Optional.empty();
        }
    }

    public Cell cell() {
        IContainer container = _container;
        // TODO I wrote this iteratively even though it might be better written recursively
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
            // _LOGGER.warn("delay(", delay, "): thread interrupted for object " + this);
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
        Cell cell = cell();
        strings.add("Coordinate=" + cell.row() + ',' + cell.col());
        strings.add("Layer=" + cell.grid().layerType());
    }

    public Direction heading() {
        return _heading;
    }

    /**
     * Only a PoweredEntity can have its power switched on or off, but putting
     * this method in this class simplifies command handling in the Server class.
     */
    public boolean isOn() {
        return true;
    }

    public void listCommands(final List<String> strings) {
        Set<String> nameSet = _commandHandlers.keySet();
        List<String> nameList = new ArrayList<>(nameSet);
        Collections.sort(nameList);
        for (String name : nameList) {
            strings.add(name);
        }
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

    @Override
    public String toString() {
        return typeName() + '{' + name + '}';
    }

    public abstract String typeName();

}
