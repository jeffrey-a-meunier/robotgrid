package robotgrid.server;

import robotgrid.utils.Result;

public abstract class CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String[] commandParts;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CommandHandler(final String[] commandParts) {
        this.commandParts = commandParts;
    }

    // Instance methods =======================================================

    public String getArg(final Command command, final int n, final String deflt) {
        int n1 = n + commandParts.length;
        if (n1 >= command.parts.length) {
            return deflt;
        }
        return command.parts[n1];
    }

    public abstract Result<Void, String> handleCommand(final Command command);

}
