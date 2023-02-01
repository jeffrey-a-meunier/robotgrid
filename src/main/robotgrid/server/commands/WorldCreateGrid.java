package robotgrid.server.commands;

import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public class WorldCreateGrid implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private Logger _logger = new Logger(WorldCreateGrid.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldCreateGrid(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public void handleCommand(final Command command) {
        // TODO Auto-generated method stub
        _logger.warn("handleCommand() is incomplete");
    }

}
