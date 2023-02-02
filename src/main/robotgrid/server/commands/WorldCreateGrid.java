package robotgrid.server.commands;

import robotgrid.scene.Cell;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandResult;
import robotgrid.utils.Logger;
import robotgrid.world.World;

public class WorldCreateGrid implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _CELL_SIZE = 50;
    protected static int _NROWS = 9;
    protected static int _NCOLS = 9;

    protected static int _NEXT_INDEX = 0;

    private Logger _logger = new Logger(WorldCreateGrid.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldCreateGrid(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public CommandResult handleCommand(final Command command) {
        int cellSize = _CELL_SIZE;
        int nRows = _NROWS;
        int nCols = _NCOLS;
        int n = 0;
        for (String argString : command.parts()) {
            switch (n++) {
                case 3:
                    nRows = Integer.parseInt(argString);
                    _logger.info("handleCommand() nRows = ", nRows);
                    break;
                case 4:
                    nCols = Integer.parseInt(argString);
                    _logger.info("handleCommand() nCols = ", nCols);
                    break;
                case 5:
                    cellSize = Integer.parseInt(argString);
                    _logger.info("handleCommand() cellSize = ", cellSize);
                    break;
            }
        }
        Cell.setSize(cellSize);
        // TODO is cellSize really needed in this constructor call?
        Grid grid = new Grid(nRows, nCols, cellSize, cellSize);
        Scene scene = new Scene(_world);
        scene.setGrid(grid);
        String name = "Grid" + (_NEXT_INDEX++);
        _world.addScene(name, scene);
        _world.setCurrentScene(name);
        return CommandResult.success();
    }

}
