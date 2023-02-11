package robotgrid.entity.abstractEntity;

import java.util.ArrayList;
import java.util.List;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.server.Client;

public class _Commands {

    // Static inner classes ===================================================

    protected static class _Info extends CommandHandler {
        public _Info() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> strings = new ArrayList<>();
            command.entity().info(strings);
            Client.COMMAND_REPLY.writeLines(strings);
        }
    }

    protected static class _ShowCommands extends CommandHandler {
        public _ShowCommands() { setImmeidate(true); }
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

    public static void setup(final AbstractEntity entity) {
        entity.addCommandHandler("Info", new _Info());
        entity.addCommandHandler("Commands", new _ShowCommands());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
