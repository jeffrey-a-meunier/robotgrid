package robotgrid.world;

import robotgrid.server.CommandHandlerRegistry;
import robotgrid.server.commands.articulatedrobot.CreateArticulatedRobot;
import robotgrid.server.commands.conveyor.CreateConveyor;
import robotgrid.server.commands.mobilerobot.CreateMobileRobot;
import robotgrid.server.commands.server.ListCommands;
import robotgrid.server.commands.server.ListControllers;
import robotgrid.server.commands.widget.CreateWidget;
import robotgrid.server.commands.world.Exit;

public class CommandSetup {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    /**
     * These are the primary commands. For each ActiveEntity instance that is
     * created, additional commands are installed for that entity.
     */
    public static void setup(final World world) {
        CommandHandlerRegistry registry = CommandHandlerRegistry.THE_REGISTRY;
        registry.register(new ListCommands(world), "Server", "list", "commands");
        registry.register(new ListControllers(world), "Server", "list", "controllers");
        registry.register(new Exit(world), "World", "exit");
        registry.register(new CreateMobileRobot(world), "new", "MobileRobot");
        registry.register(new CreateArticulatedRobot(world), "new", "ArticulatedRobot");
        registry.register(new CreateConveyor(world), "new", "Conveyor");
        registry.register(new CreateWidget(world), "new", "Widget");
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
