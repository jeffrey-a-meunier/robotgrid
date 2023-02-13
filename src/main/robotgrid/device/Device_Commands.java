package robotgrid.device;

import robotgrid.scene.Cell;
import robotgrid.server.Client;

class Device_Commands {

    // Static inner classes ===================================================

    protected static class _Coordinate extends CommandHandler {
        public _Coordinate() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Device device = (Device)command.device();
            Cell cell = device.cell();
            if (cell != null) {
                Client.COMMAND_REPLY.write("Row=", cell.row());
                Client.COMMAND_REPLY.write("Col=", cell.col());
            }
            else {
                Client.COMMAND_REPLY.write("None");
            }
        }
    }

    protected static class _Heading extends CommandHandler {
        public _Heading() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Device device = (Device)command.device();
            Client.COMMAND_REPLY.write(device.heading());
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Device device) {
        device.addCommandHandler("Coordinate", new _Coordinate());
        device.addCommandHandler("Heading", new _Heading());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
