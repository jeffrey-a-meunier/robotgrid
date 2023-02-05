package robotgrid.entity.active.controller;

import java.util.Arrays;

import robotgrid.utils.PrefixTree;
import robotgrid.utils.Result;

public abstract class CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static PrefixTree<CommandHandler> _ALL_COMMANDS = new PrefixTree<>();

    // Static initializer =====================================================
    // Static methods =========================================================

    public static CommandHandler locate(final String[] commandNameParts) {
        return _ALL_COMMANDS.lookupLongest(commandNameParts);
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String[] nameParts;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CommandHandler(final String[] nameParts) {
        this.nameParts = nameParts;
    }

    // Instance methods =======================================================

    public Result<Void, String> execute(final Controller controller, final String[] commandParts) {
        String[] arguments = Arrays.copyOfRange(commandParts, nameParts.length, commandParts.length);
        return _execute(controller, arguments);
    }

    protected abstract Result<Void, String> _execute(final Controller controller, final String[] arguments);

}
