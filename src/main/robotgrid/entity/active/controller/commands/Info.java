package robotgrid.entity.active.controller.commands;

import java.util.ArrayList;
import java.util.List;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.server.Server;
import robotgrid.utils.Result;

public class Info extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Info() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] args) {
        List<String> lines = new ArrayList<>();
        lines.add("Type: " + controller.getClass().getSimpleName());
        lines.add("Power: " + (controller.isOn() ? "On" : "Off"));
        Server.THE_SERVER.sendCommandReply("" + lines.size());
        for (String line : lines) {
            Server.THE_SERVER.sendCommandReply(line);
        }
        return new Result.Success<>();
    }

}
