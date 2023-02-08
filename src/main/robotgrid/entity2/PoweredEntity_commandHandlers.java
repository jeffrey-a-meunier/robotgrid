package robotgrid.entity2;

public class PoweredEntity_commandHandlers {

    // Static inner classes ===================================================

    protected static class _IsOn extends CommandHandler {
        @Override
        public void execute(final Command command) {
            PoweredEntity poweredEntity = (PoweredEntity)command.entity();
            command.ioContext.commandStrings("" + poweredEntity.isOn());
        }
    }

    protected static class _PowerOn extends CommandHandler {
        @Override
        public void execute(final Command command) {
            PoweredEntity poweredEntity = (PoweredEntity)command.entity();
            poweredEntity.powerOn();
        }
    }

    protected static class _PowerOff extends CommandHandler {
        @Override
        public void execute(final Command command) {
            PoweredEntity poweredEntity = (PoweredEntity)command.entity();
            poweredEntity.powerOff();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Entity2 entity) {
        entity.addCommandHandler("IsOn", new _IsOn());
        entity.addCommandHandler("PowerOn", new _PowerOn());
        entity.addCommandHandler("PowerOff", new _PowerOff());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
