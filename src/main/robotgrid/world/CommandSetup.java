package robotgrid.world;

import robotgrid.server.CommandHandlerRegistry;
import robotgrid.server.commands.articulatedrobot.CreateArticulatedRobot;
import robotgrid.server.commands.controller.CreateControllerGroup;
import robotgrid.server.commands.conveyor.CreateConveyor;
import robotgrid.server.commands.mobilerobot.CreateMobileRobot;
import robotgrid.server.commands.server.ListCommands;
import robotgrid.server.commands.server.ListControllers;
import robotgrid.server.commands.widget.CreateWidget;
import robotgrid.server.commands.world.WorldExit;

public class CommandSetup {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    /**
     * These are the primary commands. For each ActiveEntity instance that is
     * created, additional commands are installed for that entity.
     */
    public static void setup() {
        CommandHandlerRegistry registry = CommandHandlerRegistry.THE_REGISTRY;
        registry.register(new ListCommands("Server", "list", "commands"));
        registry.register(new ListControllers("Server", "list", "controllers"));
        registry.register(new WorldExit("World", "exit"));
        registry.register(new CreateMobileRobot("new", "MobileRobot"));
        registry.register(new CreateArticulatedRobot("new", "ArticulatedRobot"));
        registry.register(new CreateConveyor("new", "Conveyor"));
        registry.register(new CreateWidget("new", "Widget"));
        registry.register(new CreateControllerGroup("new", "ControllerGroup"));
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
