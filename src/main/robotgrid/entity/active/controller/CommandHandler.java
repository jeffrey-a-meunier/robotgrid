package robotgrid.entity.active.controller;

import java.util.HashMap;
import java.util.Map;

import robotgrid.utils.Result;

public abstract class CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static Map<String, CommandHandler> _ALL_HANDLERS = new HashMap<>();

    // Static initializer =====================================================
    // Static methods =========================================================

    public static CommandHandler locate(final String commandName) {
        return _ALL_HANDLERS.get(commandName);
    }

    public static void add(final String commandName, final CommandHandler handler) {
        _ALL_HANDLERS.put(commandName, handler);
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CommandHandler() {}

    // Instance methods =======================================================

    public final void execute(final Command command) {
        Result<Void, String> result = _execute(command.controller(), command.arguments());
        command.setResult(result);
    }

    protected abstract Result<Void, String> _execute(final Controller controller, final String[] arguments);

}
