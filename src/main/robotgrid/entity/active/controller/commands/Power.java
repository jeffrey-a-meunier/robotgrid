package robotgrid.entity.active.controller.commands;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class Power extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Power() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] args) {
        Server.THE_SERVER.sendCommandReply(controller.isOn() ? "On" : "Off");
        return new Result.Success<>();
    }

}
