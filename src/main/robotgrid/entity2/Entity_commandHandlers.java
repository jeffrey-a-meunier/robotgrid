package robotgrid.entity2;

import java.util.ArrayList;
import java.util.List;

public class Entity_commandHandlers {

    // Static inner classes ===================================================

    protected static class _Info extends CommandHandler {
        public _Info() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> strings = new ArrayList<>();
            // get info about the entity
            command.entity().info(strings);
            // send the strings over the info socket
            command.ioContext.commandStrings(strings);
        }
    }

    protected static class _ListCommands extends CommandHandler {
        public _ListCommands() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            List<String> strings = new ArrayList<>();
            command.entity().listCommands(strings);
            command.ioContext.commandStrings(strings);
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Entity2 entity) {
        entity.addCommandHandler("Info", new _Info());
        entity.addCommandHandler("ListCommands", new _ListCommands());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
