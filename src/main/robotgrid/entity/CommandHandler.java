package robotgrid.entity;

import robotgrid.scene.Direction;

public abstract class CommandHandler {

    // Static inner classes ===================================================

    public static class ArgumentException extends RuntimeException {
        public final String message;
        public ArgumentException(final String message) {
            this.message = message;
        }
        @Override
        public String toString() {
            return message;
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected boolean _isImmediate = false;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CommandHandler() {}

    public CommandHandler setImmeidate(final boolean isImmediate) {
        _isImmediate = isImmediate;
        return this;
    }

    // Instance methods =======================================================

    public abstract void execute(final Command command);

    public boolean isImmediate() {
        return _isImmediate;
    }

    public int getIntArg(final String paramName, final String[] arguments, final int index, final int deflt) {
        String arg = getStringArg(paramName, arguments, index);
        try {
            return Integer.parseInt(arg);
        }
        catch (final Exception exn) {
            throw new ArgumentException("Expected an integer for parameter '" + paramName + "', found '" + arg + "'");
        }
    }

    public Direction getDirectionArg(final String paramName, final String[] arguments, final int index, final Direction deflt) {
        String arg = getStringArg(paramName, arguments, index);
        if (arg == null) {
            return deflt;
        }
        Direction direction = Direction.parse(arguments[index]);
        if (direction == null) {
            throw new ArgumentException("Argument for parameter '" + paramName + "' is not a direction, found '" + arg + "'");
        }
        return direction;
    }

    public String getStringArg(final String paramName, final String[] arguments, final int index) {
        if (index >= arguments.length) {
            throw new ArgumentException("Please provide a value for parameter '" + paramName + "'");
        }
        return arguments[index];
    }

}
