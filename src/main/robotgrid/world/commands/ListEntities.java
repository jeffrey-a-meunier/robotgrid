package robotgrid.world.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import robotgrid.entity2.Command;
import robotgrid.entity2.CommandHandler;
import robotgrid.entity2.Entity2;

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
        List<String> allNames = new ArrayList<>(Entity2.names());
        Collections.sort(allNames);
        command.ioContext.commandStrings(allNames);
    }

}
