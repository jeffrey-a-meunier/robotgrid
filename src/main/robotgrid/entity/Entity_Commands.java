package robotgrid.entity;

import java.util.ArrayList;
import java.util.List;

import robotgrid.scene.Cell;
import robotgrid.server.Client;

class Entity_Commands {

    // Static inner classes ===================================================

    protected static class _Coordinate extends CommandHandler {
        public _Coordinate() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Entity entity = command.entity();
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
            Client.COMMAND_REPLY.write(command.entity().heading());
        }
    }

    protected static class _Info extends CommandHandler {
        public _Info() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> strings = new ArrayList<>();
            command.entity().info(strings);
            Client.COMMAND_REPLY.writeLines(strings);
        }
    }

    protected static class _Commands extends CommandHandler {
        public _Commands() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> commandNames = new ArrayList<>();
            command.entity().listCommands(commandNames);
            Client.COMMAND_REPLY.writeLines(commandNames);
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Entity entity) {
        entity.addCommandHandler("Coordinate", new _Coordinate());
        entity.addCommandHandler("Heading", new _Heading());
        entity.addCommandHandler("Info", new _Info());
        entity.addCommandHandler("Commands", new _Commands());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
