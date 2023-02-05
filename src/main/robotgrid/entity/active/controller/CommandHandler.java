package robotgrid.entity.active.controller;

import robotgrid.scene.Direction;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;

public abstract class CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private static Logger _logger = new Logger(CommandHandler.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public boolean _isImmediate = false;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CommandHandler() {}

    public CommandHandler setImmeidate(final boolean isImmediate) {
        _isImmediate = isImmediate;
        return this;
    }

    // Instance methods =======================================================

    protected abstract Result<Void, String> _execute(final Controller controller, final String[] arguments);

    public final void execute(final Command command) {
        Result<Void, String> result = _execute(command.controller(), command.arguments());
        command.setResult(result);
    }

    public int getIntArg(final String[] arguments, final int index, final int deflt) {
        if (index < arguments.length) {
            try {
                return Integer.parseInt(arguments[index]);
            }
            catch (final Exception exn) {
                _logger.error("String is not a number: '", arguments[index], "'");
            }
        }
        return deflt;
    }

    public String getStringArg(final String[] arguments, final int index) {
        return getStringArg(arguments, index, null);
    }

    public String getStringArg(final String[] arguments, final int index, final String deflt) {
        if (index < arguments.length) {
            return arguments[index];
        }
        return deflt;
    }

    public Direction getDirectionArg(final String[] arguments, final int index, final Direction deflt) {
        if (index < arguments.length) {
            return Direction.parse(arguments[index], deflt);
        }
        return deflt;
    }

    public boolean isImmediate() {
        return _isImmediate;
    }

}
