package robotgrid.device.group;

import java.util.Set;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.device.abstractDevice.AbstractDevice;
import robotgrid.server.Client;

public class _Commands {

    // Static inner classes ===================================================

    protected static class _Add extends CommandHandler {
        public _Add() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Group group = (Group)command.device();
            String[] args = command.arguments();
            for (String name : args) {
                AbstractDevice device = AbstractDevice.lookup(name);
                if (device == null) {
                    Client.COMMAND_REPLY.commandError(command, "Device not found '" + name + "'");
                }
                else {
                    group.add(device);
                }
            }
        }
    }

    /**
     * Indicates what devices handle a specific command name.
     */
    protected static class _WhoHandles extends CommandHandler {
        public _WhoHandles() { setImmeidate(true); }
        @Override
        public void execute(final Command command) {
            Group group = (Group)command.device();
            String[] args = command.arguments();
            for (String commandName : args) {
                Set<AbstractDevice> devices = group.whoHandles(commandName);
                if (devices == null) {
                    Client.COMMAND_REPLY.write("None");
                }
                else {
                    for (AbstractDevice device : devices) {
                        Client.COMMAND_REPLY.write(device.name);
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
