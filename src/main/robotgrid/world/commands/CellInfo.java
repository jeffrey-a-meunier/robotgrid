package robotgrid.world.commands;

import java.util.ArrayList;
import java.util.List;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.scene.Cell;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Client;
import robotgrid.world.World;

public class CellInfo extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CellInfo() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    public void execute(final Command command) {
        String[] args = command.arguments();
        try {
            int row = getIntArg("row", args, 0, 0);
            int col = getIntArg("col", args, 1, 0);
            List<String> strings = new ArrayList<>();
            Scene scene = World.THE_WORLD.currentScene();
            Grid airGrid = scene.airGrid();
            Cell airCell = airGrid.getCell(row, col);
            airCell.info(strings);
            Grid groundGrid = scene.groundGrid();
            Cell groundCell = groundGrid.getCell(row, col);
            groundCell.info(strings);
            for (String string : strings) {
                Client.COMMAND_REPLY.write(string);
            }
        }
        catch (final ArgumentException exn) {
            command.setErrorMessage("Argument exception: " + exn);
        }
    }

}
