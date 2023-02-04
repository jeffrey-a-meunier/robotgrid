package robotgrid.server.commands.server;

import java.util.Collections;
import java.util.List;

import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandHandlerRegistry;
import robotgrid.server.Server;
import robotgrid.utils.Result;
import robotgrid.world.World;

/**
 * Command:
 * World exit
 */
public class ListCommands implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListCommands(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        System.out.flush();
        List<String> commandNameList = CommandHandlerRegistry.THE_REGISTRY.allCommands();
        Collections.sort(commandNameList);
        Server.THE_SERVER.sendCommandReply("" + commandNameList.size());
        for (String name : commandNameList) {
            Server.THE_SERVER.sendCommandReply(name);
        }
        return new Result.Success<Void, String>();
    }

}
