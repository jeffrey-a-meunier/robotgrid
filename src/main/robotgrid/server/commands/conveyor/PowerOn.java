package robotgrid.server.commands.conveyor;

import robotgrid.entity.active.conveyor.Conveyor;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Result;

public class PowerOn extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Conveyor _conveyor;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOn(final Conveyor conveyor, final String ... commandParts) {
        super(commandParts);
        _conveyor = conveyor;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(Command command) {
        _conveyor.powerOn();
        return new Result.Success<Void, String>();
    }

}
