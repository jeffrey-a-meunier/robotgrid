package robotgrid.server.commands.controller;

import robotgrid.entity.active.controller.ControllerGroup;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Result;

public class PowerOn extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected ControllerGroup _group;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOn(final ControllerGroup group, final String ... commandParts) {
        super(commandParts);
        _group = group;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command_deprecated command) {
        _group.powerOn();
        return new Result.Success<Void, String>();
    }

}
