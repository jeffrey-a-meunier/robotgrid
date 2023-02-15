package robotgrid.world.commands;

import robotgrid.device.Command;
import robotgrid.device.CommandHandler;
import robotgrid.device.widget.SquareWidget;
import robotgrid.device.widget.Widget;
import robotgrid.scene.Grid;
import robotgrid.world.World;

public class CreateWidget extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateWidget() {
        setImmeidate(true);
    }

    // Instance methods =======================================================

    @Override
    public void execute(final Command command) {
        String[] args = command.arguments();
        try {
            String name = getStringArg("name", args, 0);
            if (name == null) {
                command.setErrorMessage("Widget name required");
                return;
            }
            int row = getIntArg("row", args, 1, 0);
            int col = getIntArg("col", args, 2, 0);
            Widget widget = new SquareWidget(name)
                .setColor(0xFF_FF_00_00)
                ;
            Grid grid = World.THE_WORLD.currentScene().groundGrid();
            if (!grid.addDevice(row, col, widget)) {
                command.setErrorMessage(this.getClass().getSimpleName() + " failed in cell at row " + row + ", col " + col);
            }
        }
        catch (final ArgumentException exn) {
            command.setErrorMessage("Argument exception: " + exn);
        }
    }

}
