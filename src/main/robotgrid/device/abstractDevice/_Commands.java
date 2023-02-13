package robotgrid.device.abstractDevice;

import java.util.ArrayList;
import java.util.List;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.server.Client;

public class _Commands {

    // Static inner classes ===================================================

    protected static class _Info extends CommandHandler {
        public _Info() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> strings = new ArrayList<>();
            command.device().info(strings);
            Client.COMMAND_REPLY.writeLines(strings);
        }
    }

    protected static class _ShowCommands extends CommandHandler {
        public _ShowCommands() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> commandNames = new ArrayList<>();
            command.device().listCommands(commandNames);
            Client.COMMAND_REPLY.writeLines(commandNames);
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final AbstractDevice device) {
        device.addCommandHandler("Info", new _Info());
        device.addCommandHandler("Commands", new _ShowCommands());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
