package robotgrid.server;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import robotgrid.utils.Logger;
import robotgrid.utils.PrefixTree;

@Deprecated
public class CommandHandlerRegistry_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final CommandHandlerRegistry_deprecated THE_REGISTRY = new CommandHandlerRegistry_deprecated();

    private static Logger _logger = new Logger(CommandHandlerRegistry_deprecated.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected PrefixTree<CommandHandler_deprecated> _registry = new PrefixTree<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================t

    public void register(final CommandHandler_deprecated handler) {
        _registry.insert(handler, handler.commandParts);
        _logger.info("Command added ", Arrays.toString(handler.commandParts));
    }

    public CommandHandler_deprecated get(final String ... commandParts) {
        return _registry.lookupLongest(commandParts);
    }

    public List<String> allCommands(final String prefix) {
        List<String> commands = _registry.allKeys();
        Collections.sort(commands);
        return commands;
    }

}
