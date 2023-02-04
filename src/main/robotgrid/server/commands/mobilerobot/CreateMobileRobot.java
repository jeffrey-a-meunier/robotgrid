package robotgrid.server.commands.mobilerobot;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandHandlerRegistry;
import robotgrid.utils.Result;
import robotgrid.world.World;

/**
 * Command:
 * new MobileRobot <x> <y> <heading> <name>
 */
public class CreateMobileRobot implements CommandHandler {

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

    public CreateMobileRobot(final World world) {
        _world = world;
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command command) {
        int x = 0;
        int y = 0;
        Direction direction = Direction.North;
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
                    direction = Direction.parse(part, Direction.North);
                    break;
                case 5:
                    name = part;
                    break;
            }
        }
        if (name == null) {
            name = MobileRobot.class.getSimpleName() + (_NEXT_ID++);
        }
        MobileRobot robot = new MobileRobot(name);
        robot.setDirection(direction);
        Scene scene = _world.currentScene();
        Grid grid = scene.grid();
        grid.addEntity(x, y, robot);
        _registerCommands(robot);
        return new Result.Success<Void, String>();
    }

    protected void _registerCommands(final MobileRobot robot) {
        CommandHandlerRegistry registry = CommandHandlerRegistry.THE_REGISTRY;
        String name = robot.name();
        registry.register(new MoveForward(robot), name, "move", "forward");
        registry.register(new MoveBackward(robot), name, "move", "backward");
        registry.register(new RotateRight(robot), name, "rotate", "right");
        registry.register(new RotateLeft(robot), name, "rotate", "left");
    }
}
