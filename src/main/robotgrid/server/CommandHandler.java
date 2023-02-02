package robotgrid.server;

import robotgrid.utils.Result;

public interface CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    //public CommandResult handleCommand(final Command command);
    public Result<Void, String> handleCommand(final Command command);

}
