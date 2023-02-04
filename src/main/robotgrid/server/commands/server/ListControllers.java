package robotgrid.server.commands.server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import robotgrid.entity.active.controller.Controller;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.Server;
import robotgrid.utils.Result;
import robotgrid.world.World;

/**
 * Command:
 * World exit
 */
public class ListControllers implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListControllers(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
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
