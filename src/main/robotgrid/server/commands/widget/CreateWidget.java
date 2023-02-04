package robotgrid.server.commands.widget;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.entity.widget.SquareWidget;
import robotgrid.entity.widget.Widget;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Result;
import robotgrid.world.World;

/**
 * Command:
 * new Conveyor <x> <y> <heading> <name>
 */
public class CreateWidget implements CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected World _world;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateWidget(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        int x = 0;
        int y = 0;
        String name = null;
        String[] parts = command.parts();
        for (int n=2; n<parts.length; n++) {
            String part = parts[n];
            switch (n) {
                case 2:
                    x = Integer.parseInt(part);
                    break;
                case 3:
                    y = Integer.parseInt(part);
                    break;
                case 4:
                    name = part;
                    break;
            }
        }
        if (name == null) {
            name = MobileRobot.class.getSimpleName() + (_NEXT_ID++);
        }
        Widget widget = new SquareWidget(name)
            .setFillColor(0xFF_FF_00_00)
            ;
        Scene scene = _world.currentScene();
        Grid grid = scene.grid();
        grid.addEntity(x, y, widget);
        return new Result.Success<Void, String>();
    }

}
