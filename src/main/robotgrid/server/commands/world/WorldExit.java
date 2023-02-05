package robotgrid.server.commands.world;

import robotgrid.server.Command_deprecated;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;
import robotgrid.world.World;

/**
 * Command:
 * World exit
 */
public class WorldExit extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private Logger _logger = new Logger(WorldExit.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    @Override
    public Result<Void, String> execute(Controller controller, String[] commandParts) {
        _logger.warn("World exiting");
        World.THE_WORLD.exit();
        return new Result.Success<Void, String>();
    }

}
