package robotgrid.device.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.device.abstractDevice.AbstractDevice;
import robotgrid.server.Client;

public class Group extends AbstractDevice {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static final List<String> _FORBIDDEN_COMMANDS = Arrays.asList(
        "Add", "Commands", "Coordinate", "Heading", "Info", "WhoHandles"
    );

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Set<AbstractDevice> _devices = new HashSet<>();
    protected Map<String, Set<AbstractDevice>> _commandMap = new HashMap<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Group(final String name) {
        super(name);
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    public boolean add(final AbstractDevice device) {
        boolean res = _devices.add(device);
        if (!res) {
            return false;
        }
        _mapCommands(device);
        return true;
    }

    public int count() {
        return _devices.size();
    }

    @Override
    public void info(final List<String> strings) {
        super.info(strings);
        List<String> names = new ArrayList<>();
        for (AbstractDevice device : _devices) {
            names.add(device.name);
        }
        strings.add("Devices=" + names);
        strings.add("Count=" + _devices.size());
    }

    public boolean remove(final AbstractDevice device) {
        return _devices.remove(device);
    }

    @Override
    public String typeName() {
        return "Group";
    }

    public Set<AbstractDevice> whoHandles(final String commandName) {
        return _commandMap.get(commandName);
    }

    protected static class _CommandHandler extends CommandHandler {
        public _CommandHandler() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Group group = (Group)command.device();
            String commandName = command.commandName();
            Set<AbstractDevice> devices = group._commandMap.get(commandName);
            if (devices == null) {
                Client.COMMAND_REPLY.commandError(command, "No devices found that handle command '" + commandName + "'");
            }
            else {
                for (AbstractDevice device : devices) {
                    Command command2 = command.copyWithDeviceName(device.name);
                    command2.performLifecycle();
                }
            }
        }
    }

    protected void _mapCommands(final AbstractDevice device) {
        List<String> commandNames = new ArrayList<>();
        device.listCommands(commandNames);
        for (String commandName : commandNames) {
            if (_FORBIDDEN_COMMANDS.contains(commandName)) {
                continue;
            }
            Set<AbstractDevice> devices = _commandMap.get(commandName);
            if (devices == null) {
                devices = new HashSet<>();
                _commandMap.put(commandName, devices);
            }
            devices.add(device);
            addCommandHandler(commandName, new _CommandHandler());
        }
    }

}
