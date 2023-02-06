package robotgrid.entity.active.controller.commands;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.utils.Result;

public class PowerOn extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public PowerOn() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] args) {
        controller.powerOn();
        return new Result.Success<>();
    }

}
