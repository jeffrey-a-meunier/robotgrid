package robotgrid.world;

import robotgrid.entity.active.controller.CommandHandler;

public class CommandSetup {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup() {
        CommandHandler.add("exit", new WorldController.Exit());
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

}
