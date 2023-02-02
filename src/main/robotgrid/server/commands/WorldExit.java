package robotgrid.server.commands;

import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandResult;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public class WorldExit implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private Logger _logger = new Logger(WorldExit.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldExit(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public CommandResult handleCommand(final Command command) {
        _logger.warn("World exiting");
        _world.exit();
        return CommandResult.success();
    }

}
