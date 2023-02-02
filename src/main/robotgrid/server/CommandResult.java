package robotgrid.server;

/**
 * Use the static methods success() and failure(String) to construct result
 * instances.
 */
public abstract class CommandResult {

    // Static inner classes ===================================================

    public static class Success extends CommandResult {
        public Success() {
            super(true);
        }
    }

    public static class Failure extends CommandResult {
        public final String reason;

        public Failure(final String reason) {
            super(false);
            this.reason = reason;
        }
    }

    // Static variables =======================================================

    protected static final Success _SUCCESS = new Success();

    // Static initializer =====================================================
    // Static methods =========================================================

    public static CommandResult success() {
        return _SUCCESS;
    }

    public static CommandResult failure(final String reason) {
        return new Failure(reason);
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final boolean isSuccess;
    public final boolean isFailure;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CommandResult(final boolean isSuccess) {
        this.isSuccess = isSuccess;
        this.isFailure = !isSuccess;
    }

    // Instance methods =======================================================

}
