package robotgrid.entity.active.robot;

import robotgrid.utils.Result;
import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;

public class RotatingBaseController extends Controller {

    // Static inner classes ===================================================

    protected static class RotateLeftCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
            RotatingBase base = (RotatingBase)controller.entity();
            return base.rotateLeft();
        }
    }

    protected static class RotateRightCommand extends CommandHandler {
        @Override
        public Result<Void, String> execute(final Controller controller) {
            RotatingBase base = (RotatingBase)controller.entity();
            return base.rotateRight();
        }
    }

    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public RotatingBaseController(final String name) {
        super(name);
        // installCommand("RotateRight", new RotateRightCommand());
        // installCommand("RotateLeft", new RotateLeftCommand());
    }

    // Instance methods =======================================================

}
