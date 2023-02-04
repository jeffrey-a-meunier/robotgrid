package robotgrid.server.commands.conveyor;

import robotgrid.entity.active.conveyor.Conveyor;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandHandlerRegistry;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class CreateConveyor extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateConveyor(final String ... commandParts) {
        super(commandParts);
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        int x = Integer.parseInt(getArg(command, 0, "0"));
        int y = Integer.parseInt(getArg(command, 1, "0"));
        Direction direction = Direction.parse(getArg(command, 2, "North"), Direction.North);
        String name = getArg(command, 3, null);
        if (name == null) {
            name = Conveyor.class.getSimpleName() + (_NEXT_ID++);
        }
        Conveyor conveyor = new Conveyor(name);
        conveyor.setDirection(direction);
        Scene scene = World.THE_WORLD.currentScene();
        Grid grid = scene.grid();
        grid.addEntity(x, y, conveyor);
        _registerCommands(conveyor);
        return new Result.Success<Void, String>();
    }

    protected void _registerCommands(final Conveyor conveyor) {
        CommandHandlerRegistry registry = CommandHandlerRegistry.THE_REGISTRY;
        String name = conveyor.name;
        registry.register(new PowerOn(conveyor, name, "power", "on"));
        registry.register(new PowerOff(conveyor, name, "power", "off"));
    }

}
