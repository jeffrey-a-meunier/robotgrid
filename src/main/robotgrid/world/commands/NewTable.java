package robotgrid.world.commands;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.utils.Result;

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
    protected Result<Void, String> _execute(final Controller controller, final String[] arguments) {
        String name = getStringArg(arguments, 0);
        if (name == null) {
            return new Result.Failure<Void,String>("Name not supplied");
        }
        int row = getIntArg(arguments, 1, 0);
        int col = getIntArg(arguments, 2, 0);
        // Table table = new Table(name);
        // Grid grid = World.THE_WORLD.currentScene().grid();
        // if (grid.addEntity(row, col, table)) {
        //     return new Result.Success<Void, String>();
        // }
        return new Result.Failure<Void,String>("Unable to add " + this.getClass().getSimpleName() + " to grid at " + row + ", " + col);
    }

}
