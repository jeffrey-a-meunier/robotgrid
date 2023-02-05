package robotgrid.server;

import java.util.Arrays;

import robotgrid.utils.PrefixTree;
import robotgrid.utils.Result;
import robotgrid.utils.UID;

/**
 * Every command must be a string of the form
 * "<receiver> <verb> [<arguments>]"
 */
@Deprecated
public class Command_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected PrefixTree<Command_deprecated> _ALL_COMMANDS = new PrefixTree<>();

    // Static initializer =====================================================
    // Static methods =========================================================

    public static Command_deprecated locate(final String[] commandNameParts) {
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String[] parts;

    protected UID _uid = new UID();
    
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command_deprecated(final String string) {
        parts = string.split(" ");
    }

    // Instance methods =======================================================

    public CommandHandler_deprecated getHandler() {
        return CommandHandlerRegistry_deprecated.THE_REGISTRY.get(parts);
    }

    @Deprecated
    final Result<Void, String> execute() {
        CommandHandler_deprecated handler = CommandHandlerRegistry_deprecated.THE_REGISTRY.get(parts);
        return handler.handleCommand(this);
    }

    @Override
    public String toString() {
        return "Command{" + Arrays.toString(parts) + ", " + _uid + '}';
    }

    public UID uid() {
        return _uid;
    }

}
