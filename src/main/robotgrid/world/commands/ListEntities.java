package robotgrid.world.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.entity.Entity;
import robotgrid.server.Client;

public class ListEntities extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListEntities() {
        setImmeidate(true);
    }

    // Instance methods =======================================================
    
    @Override
    public void execute(final Command command) {
        List<String> allNames = new ArrayList<>(Entity.names());
        Collections.sort(allNames);
        for (String name : allNames) {
            Client.COMMAND_REPLY.write(name);
        }
    }

}
