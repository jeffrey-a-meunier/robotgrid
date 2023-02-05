package robotgrid.world;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;

public class WorldController extends Controller {

    // Static inner classes ===================================================

    public static class Exit extends CommandHandler {
        @Override
        protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
            _logger.warn("World exiting");
            World.THE_WORLD.exit();
            return new Result.Success<Void, String>();
        }
    }

    // Static variables =======================================================

    private static Logger _logger = new Logger(WorldController.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldController() {
        super("World");
    }

    // Instance methods =======================================================

}
