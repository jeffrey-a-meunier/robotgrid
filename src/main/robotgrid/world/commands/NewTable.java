package robotgrid.world.commands;

import robotgrid.entity.Command;
import robotgrid.entity.CommandHandler;
import robotgrid.entity.table.Table;
import robotgrid.scene.Grid;
import robotgrid.world.World;

public class NewTable extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public NewTable() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    public void execute(final Command command) {
        String[] args = command.arguments();
        try {
            String name = getStringArg("name", args, 0);
            if (name == null) {
                command.setErrorMessage("Table name required");
                return;
            }
            int row = getIntArg("row", args, 1, 0);
            int col = getIntArg("col", args, 2, 0);
            Table table = new Table(name);
            Grid grid = World.THE_WORLD.currentScene().grid();
            if (!grid.addEntity(row, col, table)) {
                command.setErrorMessage("Unable to add " + this.getClass().getSimpleName() + " to grid at " + row + ", " + col);
            }
        }
        catch (final ArgumentException exn) {
            command.setErrorMessage("Argument exception: " + exn);
        }
    }

}
