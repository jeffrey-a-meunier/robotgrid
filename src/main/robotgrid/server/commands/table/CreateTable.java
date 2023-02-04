package robotgrid.server.commands.table;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.entity.fixture.Table;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class CreateTable extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateTable(final String ... commandParts) {
        super(commandParts);
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        int x = Integer.parseInt(getArg(command, 0, "0"));
        int y = Integer.parseInt(getArg(command, 1, "0"));
        String name = getArg(command, 2, null);
        if (name == null) {
            name = MobileRobot.class.getSimpleName() + (_NEXT_ID++);
        }
        Table table = new Table(name);
        Scene scene = World.THE_WORLD.currentScene();
        Grid grid = scene.grid();
        grid.addEntity(x, y, table);
        _registerCommands(table);
        return new Result.Success<Void, String>();
    }

    protected void _registerCommands(final Table table) {
        // CommandHandlerRegistry registry = CommandHandlerRegistry.THE_REGISTRY;
        // String name = table.name;
    }
}
