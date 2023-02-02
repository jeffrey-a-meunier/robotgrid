package robotgrid.server.commands;

import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandResult;
import robotgrid.utils.Logger;
import robotgrid.world.World;

@Deprecated
public class WorldCreate implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private Logger _logger = new Logger(WorldCreate.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldCreate(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public CommandResult handleCommand(final Command command) {
        // TODO Auto-generated method stub
        String message = "handleCommand() is incomplete";
        _logger.warn(message);
        return CommandResult.failure(message);
    }

}
