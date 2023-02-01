package robotgrid.server;

import java.util.Arrays;

import robotgrid.utils.Logger;
import robotgrid.utils.PrefixTree;

public class CommandHandlerRegistry {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final CommandHandlerRegistry THE_REGISTRY = new CommandHandlerRegistry();

    private static Logger _logger = new Logger(CommandHandlerRegistry.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected PrefixTree<CommandHandler> _registry = new PrefixTree<>();

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================t

    public void register(final CommandHandler handler, final String ... commandParts) {
        _registry.insert(handler, commandParts);
        _logger.info("Command added: ", Arrays.toString(commandParts));
    }

    public CommandHandler get(final String ... commandParts) {
        return _registry.lookupLongest(commandParts);
    }

}
