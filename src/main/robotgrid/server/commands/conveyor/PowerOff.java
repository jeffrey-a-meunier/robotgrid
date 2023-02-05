package robotgrid.server.commands.conveyor;

import robotgrid.entity.active.conveyor.Conveyor;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Result;

public class PowerOff extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Conveyor _conveyor;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOff(final Conveyor conveyor, final String ... commandParts) {
        super(commandParts);
        _conveyor = conveyor;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command_deprecated command) {
        _conveyor.powerOff();
        return new Result.Success<Void, String>();
    }

}
