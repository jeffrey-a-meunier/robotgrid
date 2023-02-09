package robotgrid.entity;

class PoweredEntity_Commands {

    // Static inner classes ===================================================

    protected static class _IsOn extends CommandHandler {
        public _IsOn() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            PoweredEntity poweredEntity = (PoweredEntity)command.entity();
            command.ioContext.commandStrings("" + poweredEntity.isOn());
        }
    }

    protected static class _PowerOn extends CommandHandler {
        public _PowerOn() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            PoweredEntity poweredEntity = (PoweredEntity)command.entity();
            poweredEntity.powerOn();
        }
    }

    protected static class _PowerOff extends CommandHandler {
        public _PowerOff() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            PoweredEntity poweredEntity = (PoweredEntity)command.entity();
            poweredEntity.powerOff();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Entity entity) {
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
