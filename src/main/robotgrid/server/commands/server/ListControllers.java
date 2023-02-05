package robotgrid.server.commands.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import robotgrid.entity.active.controller.Controller;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class ListControllers extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListControllers(final String ... commandParts) {
        super(commandParts);
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command_deprecated command) {
        Set<String> controllerNameSet = Controller.names();
        List<String> controllerNameList = new ArrayList<>(controllerNameSet);
        Collections.sort(controllerNameList);
        Server.THE_SERVER.sendCommandReply("" + controllerNameList.size());
        for (String name : controllerNameList) {
            Server.THE_SERVER.sendCommandReply(name);
        }
        return new Result.Success<Void, String>();
    }

}
