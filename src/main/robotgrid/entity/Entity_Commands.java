package robotgrid.entity;

import java.util.ArrayList;
import java.util.List;

import robotgrid.server.Client;

class Entity_Commands {

    // Static inner classes ===================================================

    protected static class _Info extends CommandHandler {
        public _Info() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> strings = new ArrayList<>();
            // get info about the entity
            command.entity().info(strings);
            // send the strings over the info socket
            Client.COMMAND_REPLY.write(strings);
        }
    }

    protected static class _ListCommands extends CommandHandler {
        public _ListCommands() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> commandNames = new ArrayList<>();
            command.entity().listCommands(commandNames);
            for (String commandName : commandNames) {
                Client.COMMAND_REPLY.write(commandName);
            }
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Entity entity) {
        entity.addCommandHandler("Info", new _Info());
        entity.addCommandHandler("ListCommands", new _ListCommands());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
