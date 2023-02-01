package robotgrid.server;

import robotgrid.utils.PrefixTree;

public class CommandHandlerReistry {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final CommandHandlerReistry THE_REGISTRY = new CommandHandlerReistry();

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
    }

    public CommandHandler get(final String ... commandParts) {
        return _registry.lookupLongest(commandParts);
    }

}
