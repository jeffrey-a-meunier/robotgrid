package robotgrid.world;

import robotgrid.entity.active.controller.Command;
import robotgrid.entity.active.controller.Controller;
import robotgrid.world.commands.Exit;
import robotgrid.world.commands.ListCommands;
import robotgrid.world.commands.ListControllers;
import robotgrid.world.commands.NewArticulatedRobot;
import robotgrid.world.commands.NewControllerGroup;
import robotgrid.world.commands.NewConveyor;
import robotgrid.world.commands.NewMobileRobot;
import robotgrid.world.commands.NewTable;
import robotgrid.world.commands.NewWidget;

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
        addCommandHandler("Exit", new Exit());
        addCommandHandler("ListCommands", new ListCommands());
        addCommandHandler("ListControllers", new ListControllers());
        addCommandHandler("NewMobileRobot", new NewMobileRobot());
        addCommandHandler("NewArticulatedRobot", new NewArticulatedRobot());
        addCommandHandler("NewConveyor", new NewConveyor());
        addCommandHandler("NewWidget", new NewWidget());
        addCommandHandler("NewControllerGroup", new NewControllerGroup());
        addCommandHandler("NewTable", new NewTable());
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
