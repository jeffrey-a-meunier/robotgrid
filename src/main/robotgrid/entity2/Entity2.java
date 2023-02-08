package robotgrid.entity2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;

public abstract class Entity2 {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static final Map<String, Entity2> _ALL_ENTITIES = new HashMap<>();

    // Static initializer =====================================================
    // Static methods =========================================================

    public static Entity2 lookup(final String name) {
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
    protected Cell _cell;
    protected Direction _heading;
    protected Entity2 _payload;

    protected Map<String, CommandHandler> _commandHandlers = new HashMap<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Entity2(final String name) {
        this(name, 0);
    }

    public Entity2(final String name, final int height) {
        this.name = name;
        this.height = height;
        Entity_commandHandlers.setup(this);
        _ALL_ENTITIES.put(name, this);
    }

    public Entity2 setHeading(final Direction heading) {
        _heading = heading;
        return this;
    }

    public Entity2 setView(final View view) {
        _view = view;
        return this;
    }

    // Instance methods =======================================================

    public void addCommandHandler(final String name, final CommandHandler handler) {
        _commandHandlers.put(name, handler);
    }

    public boolean addPayload(final Entity2 payload) {
        if (_payload == null) {
            return false;
        }
        _payload = payload;
        return true;
    }

    public Entity2 removePayload() {
        Entity2 entity = _payload;
        _payload = null;
        return entity;
    }

    public Entity2 payload() {
        return _payload;
    }

    public Cell cell() {
        return _cell;
    }

    public void setCell(final Cell cell) {
        _cell = cell;
    }

    public void draw(final Graphics graphics, final int layerNum) {
        _view.draw(graphics, layerNum);
    }

    public void info(final List<String> strings) {
        strings.add("Type=" + getClass().getSimpleName());
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
        System.out.println("Entity2.sendCommand " + command);
        command.execute();
    }

}
