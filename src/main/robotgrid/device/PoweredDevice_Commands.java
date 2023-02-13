package robotgrid.device;

import robotgrid.server.Client;

class PoweredDevice_Commands {

    // Static inner classes ===================================================

    protected static class _Power extends CommandHandler {
        public _Power() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            PoweredDevice poweredDevice = (PoweredDevice)command.device();
            Client.COMMAND_REPLY.write((poweredDevice.isOn() ? "On" : "Off"));
        }
    }

    protected static class _PowerOn extends CommandHandler {
        public _PowerOn() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            PoweredDevice poweredDevice = (PoweredDevice)command.device();
            poweredDevice.powerOn();
        }
    }

    protected static class _PowerOff extends CommandHandler {
        public _PowerOff() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            PoweredDevice poweredDevice = (PoweredDevice)command.device();
            poweredDevice.powerOff();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Device device) {
        device.addCommandHandler("Power", new _Power());
        device.addCommandHandler("PowerOn", new _PowerOn());
        device.addCommandHandler("PowerOff", new _PowerOff());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
