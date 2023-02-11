package robotgrid.entity.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.entity.abstractEntity.AbstractEntity;
import robotgrid.server.Client;

public class Group extends AbstractEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static final List<String> _FORBIDDEN_COMMANDS = Arrays.asList(
        "Add", "Commands", "Coordinate", "Heading", "Info", "WhoHandles"
    );

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Set<AbstractEntity> _entities = new HashSet<>();
    protected Map<String, Set<AbstractEntity>> _commandMap = new HashMap<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Group(final String name) {
        super(name);
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    public boolean add(final AbstractEntity entity) {
        boolean res = _entities.add(entity);
        if (!res) {
            return false;
        }
        _mapCommands(entity);
        return true;
    }

    public int count() {
        return _entities.size();
    }

    @Override
    public void info(final List<String> strings) {
        super.info(strings);
        List<String> names = new ArrayList<>();
        for (AbstractEntity entity : _entities) {
            names.add(entity.name);
        }
        strings.add("Entities=" + names);
        strings.add("Count=" + _entities.size());
    }

    public boolean remove(final AbstractEntity entity) {
        return _entities.remove(entity);
    }

    @Override
    public String typeName() {
        return "Group";
    }

    public Set<AbstractEntity> whoHandles(final String commandName) {
        return _commandMap.get(commandName);
    }

    protected static class _CommandHandler extends CommandHandler {
        public _CommandHandler() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Group group = (Group)command.entity();
            String commandName = command.commandName();
            Set<AbstractEntity> entities = group._commandMap.get(commandName);
            if (entities == null) {
                Client.COMMAND_REPLY.commandError(command, "No entities found that handle command '" + commandName + "'");
            }
            else {
                for (AbstractEntity entity : entities) {
                    Command command2 = command.copyWithEntityName(entity.name);
                    command2.performLifecycle();
                }
            }
        }
    }

    protected void _mapCommands(final AbstractEntity entity) {
        List<String> commandNames = new ArrayList<>();
        entity.listCommands(commandNames);
        for (String commandName : commandNames) {
            if (_FORBIDDEN_COMMANDS.contains(commandName)) {
                continue;
            }
            Set<AbstractEntity> entities = _commandMap.get(commandName);
            if (entities == null) {
                entities = new HashSet<>();
                _commandMap.put(commandName, entities);
            }
            entities.add(entity);
            addCommandHandler(commandName, new _CommandHandler());
        }
    }

}
