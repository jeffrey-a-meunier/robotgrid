package robotgrid.world.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.server.Server;
import robotgrid.utils.Result;

@Deprecated
public class ListCommands extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ListCommands() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
        Collection<Controller> allControllers = Controller.controllers();
        List<String> commandStrings = new ArrayList<>();
        for (Controller controller1 : allControllers) {
            String controllerName = controller1.name;
            Set<String> controllerCommandNames = controller1.commandNames();
            for (String commandName : controllerCommandNames) {
                commandStrings.add(controllerName + ' ' + commandName);
            }
        }
        Collections.sort(commandStrings);
        Server.THE_SERVER.sendCommandReply("" + commandStrings.size());
        for (String commandString : commandStrings) {
            Server.THE_SERVER.sendCommandReply(commandString);
        }
        return new Result.Success<Void, String>();
    }

}
