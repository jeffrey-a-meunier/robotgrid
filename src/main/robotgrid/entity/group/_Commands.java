package robotgrid.entity.group;

import java.util.Set;

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

    /**
     * Indicates what entities handle a specific command name.
     */
    protected static class _WhoHandles extends CommandHandler {
        public _WhoHandles() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            System.out.println("_Who called " + command);
            Group group = (Group)command.entity();
            String[] args = command.arguments();
            for (String commandName : args) {
                System.out.println("_Who got command name '" + commandName + "'");
                Set<AbstractEntity> entities = group.whoHandles(commandName);
                System.out.println("  entities = " + entities);
                if (entities == null) {
                    Client.COMMAND_REPLY.write("None");
                }
                else {
                    for (AbstractEntity entity : entities) {
                        Client.COMMAND_REPLY.write(entity.name);
                    }
                }
            }
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final Group group) {
        group.addCommandHandler("Add", new _Add());
        group.addCommandHandler("WhoHandles", new _WhoHandles());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
