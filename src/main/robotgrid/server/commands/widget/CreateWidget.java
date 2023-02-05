package robotgrid.server.commands.widget;

import robotgrid.entity.widget.SquareWidget;
import robotgrid.entity.widget.Widget;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class CreateWidget extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateWidget(final String ... commandParts) {
        super(commandParts);
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command_deprecated command) {
        int x = Integer.parseInt(getArg(command, 0, "0"));
        int y = Integer.parseInt(getArg(command, 1, "0"));
        String name = getArg(command, 2, null);
        if (name == null) {
            name = Widget.class.getSimpleName() + (_NEXT_ID++);
        }
        Widget widget = new SquareWidget(name)
            .setFillColor(0xFF_FF_00_00)
            ;
        Scene scene = World.THE_WORLD.currentScene();
        Grid grid = scene.grid();
        grid.addEntity(x, y, widget);
        return new Result.Success<Void, String>();
    }

}
