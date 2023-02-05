package robotgrid.world.commands;

import robotgrid.entity.active.controller.CommandHandler;
import robotgrid.entity.active.controller.Controller;
import robotgrid.entity.widget.SquareWidget;
import robotgrid.entity.widget.Widget;
import robotgrid.scene.Grid;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class NewWidget extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public NewWidget() {
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
        Widget widget = new SquareWidget(name)
            .setFillColor(0xFF_FF_00_00)
            ;
        Grid grid = World.THE_WORLD.currentScene().grid();
        if (grid.addEntity(row, col, widget)) {
            return new Result.Success<Void, String>();
        }
        return new Result.Failure<Void,String>("Unable to add " + this.getClass().getSimpleName() + " to grid at " + row + ", " + col);
    }

}
