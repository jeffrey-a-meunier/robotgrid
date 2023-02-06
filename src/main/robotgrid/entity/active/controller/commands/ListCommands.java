package robotgrid.entity.active.controller.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class ListCommands extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Map<String, CommandHandler> _handlers;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListCommands(final Map<String, CommandHandler> handlers) {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] args) {
        Set<String> commandNames = _handlers.keySet();
        List<String> commandNameList = new ArrayList<>(commandNames);
        Collections.sort(commandNameList);
        Server.THE_SERVER.sendCommandReply("" + commandNameList.size());
        for (String commandName : commandNameList) {
            Server.THE_SERVER.sendCommandReply(commandName);
        }
        return new Result.Success<>();
    }

}
