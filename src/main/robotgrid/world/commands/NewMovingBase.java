package robotgrid.world.commands;

import robotgrid.entity2.Command;
import robotgrid.entity2.CommandHandler;
import robotgrid.entity2.movingBase.MovingBase;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.world.World;

public class NewMovingBase extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public NewMovingBase() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    public void execute(final Command command) {
        String[] args = command.arguments();
        try {
            String name = getStringArg("name", args, 0);
            if (name == null) {
                command.setErrorMessage("Entity name not supplied");
                return;
            }
            int row = getIntArg("row", args, 1, 0);
            int col = getIntArg("col", args, 2, 0);
            Direction heading = getDirectionArg("heading", args, 3, Direction.North);
            MovingBase base = (MovingBase)new MovingBase(name)
                .setHeading(heading)
                ;
            Grid grid = World.THE_WORLD.currentScene().grid();
            if (!grid.addEntity(row, col, base)) {
                command.setErrorMessage("Unable to add " + this.getClass().getSimpleName() + " to grid at " + row + ", " + col);
            }
        }
        catch (final ArgumentException exn) {
            command.setErrorMessage("Argument exception: " + exn);
        }
    }

}
