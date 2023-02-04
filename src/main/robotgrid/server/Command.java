package robotgrid.server;

import java.util.Arrays;

import robotgrid.utils.Result;
import robotgrid.utils.UID;

/**
 * Every command must be a string of the form
 * "<receiver> <verb> [<arguments>]"
 */
public class Command {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String[] parts;

    protected UID _uid = new UID();
    
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Command(final String string) {
        parts = string.split(" ");
    }

    // Instance methods =======================================================

    public CommandHandler getHandler() {
        return CommandHandlerRegistry.THE_REGISTRY.get(parts);
    }

    @Deprecated
    final Result<Void, String> execute() {
        CommandHandler handler = CommandHandlerRegistry.THE_REGISTRY.get(parts);
        // handleCommand does not return until the command is complete
        Result<Void, String> res = handler.handleCommand(this);
        return res;
    }

    @Override
    public String toString() {
        return "Command{" + Arrays.toString(parts) + ", " + _uid + '}';
    }

    public UID uid() {
        return _uid;
    }

}
