package robotgrid.world.commands;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class Exit extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private static Logger _logger = new Logger(Exit.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Exit() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
        _logger.info("World exiting");
        World.THE_WORLD.exit();
        return new Result.Success<Void, String>();
    }

}
