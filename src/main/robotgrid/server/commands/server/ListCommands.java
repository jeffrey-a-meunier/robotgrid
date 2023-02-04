package robotgrid.server.commands.server;

import java.util.Collections;
import java.util.List;

import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandHandlerRegistry;
import robotgrid.server.Server;
import robotgrid.utils.Result;

/**
 * Command:
 * World exit
 */
public class ListCommands extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListCommands(final String ... commandParts) {
        super(commandParts);
   }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        String prefix = getArg(command, 0, null);
        List<String> commandNameList = CommandHandlerRegistry.THE_REGISTRY.allCommands(prefix);
        Collections.sort(commandNameList);
        Server.THE_SERVER.sendCommandReply("" + commandNameList.size());
        for (String name : commandNameList) {
            Server.THE_SERVER.sendCommandReply(name);
        }
        return new Result.Success<Void, String>();
    }

}
