package robotgrid.entity.active.controller;

public abstract class CommandResult {

    public static final Success SUCCESS = new Success();

    public static class Success extends CommandResult {
        final boolean isFailure = false;
    }

    public static class Failure extends CommandResult {
        public final boolean isFailure = true;
        public final String reason;

        public Failure(final String reason) {
            this.reason = reason;
        }
    }

    public static class NotImplemented extends Failure {
        public final String opcode;
        public final Controller controller;

        public NotImplemented(final String opcode, final Controller controller) {
            super("Opcode not implemented in controller");
            this.opcode = opcode;
            this.controller = controller;
        }
    }

}
