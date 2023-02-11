package robotgrid.entity;

import robotgrid.scene.Cell;
import robotgrid.server.Client;

class Entity_Commands {

    // Static inner classes ===================================================

    protected static class _Coordinate extends CommandHandler {
        public _Coordinate() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Entity entity = (Entity)command.entity();
            Cell cell = entity.cell();
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
            Entity entity = (Entity)command.entity();
            Client.COMMAND_REPLY.write(entity.heading());
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Entity entity) {
        entity.addCommandHandler("Coordinate", new _Coordinate());
        entity.addCommandHandler("Heading", new _Heading());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
