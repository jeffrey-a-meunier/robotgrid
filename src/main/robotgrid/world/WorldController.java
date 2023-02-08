package robotgrid.world;

import robotgrid.entity.active.controller.Command;
import robotgrid.entity.active.controller.Controller;

@Deprecated
public class WorldController extends Controller {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldController() {
        super("World", false);
        // addCommandHandler("Exit", new Exit());
        // addCommandHandler("ListCommands", new ListCommands());
        // addCommandHandler("ListControllers", new ListControllers());
        // addCommandHandler("NewMovingBase", new NewMovingBase());
        // addCommandHandler("NewArticulatedRobot", new NewArticulatedRobot());
        // addCommandHandler("NewConveyor", new NewConveyor());
        // addCommandHandler("NewWidget", new NewWidget());
        // addCommandHandler("NewControllerGroup", new NewControllerGroup());
        // addCommandHandler("NewTable", new NewTable());
    }

    // Instance methods =======================================================

    @Override
    public void powerOff() {
    }

    @Override
    public boolean sendCommand(final Command command) {
        command.execute();
        return true;
    }

}
