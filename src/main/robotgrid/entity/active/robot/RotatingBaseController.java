package robotgrid.entity.active.robot;

import robotgrid.entity.active.controller.ICommand;
import robotgrid.utils.Result;
import robotgrid.entity.active.controller.Controller;

public class RotatingBaseController extends Controller {

    // Static inner classes ===================================================

    protected static class RotateLeftCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
            RotatingBase base = (RotatingBase)controller.entity();
            return base.rotateLeft();
        }
    }

    protected static class RotateRightCommand implements ICommand {
        @Override
        public Result<Void, String> execute(final Controller controller, final String[] parts) {
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
        installCommand("RotateRight", new RotateRightCommand());
        installCommand("RotateLeft", new RotateLeftCommand());
    }

    // Instance methods =======================================================

}
