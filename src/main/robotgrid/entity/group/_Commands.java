package robotgrid.entity.group;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.entity.abstractEntity.AbstractEntity;
import robotgrid.server.Client;

public class _Commands {

    // Static inner classes ===================================================

    protected static class _Add extends CommandHandler {
        public _Add() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Group group = (Group)command.entity();
            String[] args = command.arguments();
            for (String name : args) {
                AbstractEntity entity = AbstractEntity.lookup(name);
                if (entity == null) {
                    Client.COMMAND_REPLY.commandError(command, "Entity not found '" + name + "'");
                }
                else {
                    group.add(entity);
                }
            }
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Group group) {
        group.addCommandHandler("Add", new _Add());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
