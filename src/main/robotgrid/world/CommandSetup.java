package robotgrid.world;

import robotgrid.server.CommandHandlerRegistry;
import robotgrid.server.commands.WorldExit;

public class CommandSetup {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final World world) {
        CommandHandlerRegistry.THE_REGISTRY.register(new WorldExit(world), "World", "Exit");
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
