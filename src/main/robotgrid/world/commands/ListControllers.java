package robotgrid.world.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class ListControllers extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListControllers() {
        setImmeidate(true);
    }

    // Instance methods =======================================================
    
    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
        List<String> allNames = new ArrayList<>(Controller.names());
        Collections.sort(allNames);
        Server.THE_SERVER.sendCommandReply("" + allNames.size());
        for (String name : allNames) {
            Server.THE_SERVER.sendCommandReply(name);
        }
        return new Result.Success<Void, String>();
    }

}
