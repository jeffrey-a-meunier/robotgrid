package robotgrid.server.commands.controller;

import robotgrid.entity.active.controller.Controller;
import robotgrid.entity.active.controller.ControllerGroup;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class ControllerGroupAdd extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected ControllerGroup _group;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ControllerGroupAdd(final ControllerGroup group, final String ... commandParts) {
        super(commandParts);
        _group = group;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command command) {
        for (int n=0; ; n++) {
            String controllerName = getArg(command, n, null);
            if (controllerName == null) {
                break;
            }
            _addController(controllerName);
        }
        return new Result.Success<Void, String>();
    }

    protected void _addController(final String controllerName) {
        Controller controller = Controller.lookup(controllerName);
        if (controller != null) {
            _group.add(controller);
        }
        else {
            Server.THE_SERVER.sendCommandReply("Controller not found: '" + controllerName + '\'');
        }
    }

}
