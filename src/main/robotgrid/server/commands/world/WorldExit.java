package robotgrid.server.commands.world;

import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
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

    public WorldExit(final String ... commandParts) {
        super(commandParts);
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        _logger.warn("World exiting");
        World.THE_WORLD.exit();
        return new Result.Success<Void, String>();
    }

}
