package robotgrid.world.commands;

import java.util.Collections;
import java.util.List;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.device.abstractDevice.AbstractDevice;
import robotgrid.server.Client;

public class Devices extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Devices() {
        setImmeidate(true);
    }

    // Instance methods =======================================================
    
    @Override
    public void execute(final Command command) {
        List<String> allNames = AbstractDevice.list();
        Collections.sort(allNames);
        Client.COMMAND_REPLY.writeLines(allNames);
    }

}
