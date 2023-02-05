package robotgrid.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import robotgrid.entity.active.controller.Command;
import robotgrid.utils.Logger;
import robotgrid.utils.PrefixTree;

public class CommandRegistry {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final CommandRegistry THE_REGISTRY = new CommandRegistry();

    private static Logger _logger = new Logger(CommandRegistry.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected PrefixTree<Command> _registry = new PrefixTree<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public void register(final String[] commandParts, final Command command) {
        _registry.insert(command, commandParts);
        _logger.info("Command added ", Arrays.toString(commandParts));
    }

    public Command locate(final String ... commandParts) {
        return _registry.lookupLongest(commandParts);
    }

    public List<String> allCommands(final String prefix) {
        List<String> commands = _registry.allKeys();
        Collections.sort(commands);
        return commands;
    }

}
