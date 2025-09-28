package robotgrid.world.commands;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.server.Client;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public class Exit extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    private static Logger _LOGGER = new Logger(Exit.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Exit() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    public void execute(final Command command) {
        String message = "World exiting";
        Client.FEEDBACK.write(message);
        _LOGGER.info(message);
        World.THE_WORLD.exit();
    }

}
