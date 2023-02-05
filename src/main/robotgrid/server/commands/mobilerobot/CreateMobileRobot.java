package robotgrid.server.commands.mobilerobot;

import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command_deprecated;
import robotgrid.server.CommandHandler_deprecated;
import robotgrid.server.CommandHandlerRegistry_deprecated;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class CreateMobileRobot extends CommandHandler_deprecated {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateMobileRobot(final String ... commandParts) {
        super(commandParts);
    }

    // Instance methods =======================================================

    @Override
    public Result<Void, String> handleCommand(final Command_deprecated command) {
        int x = Integer.parseInt(getArg(command, 0, "0"));
        int y = Integer.parseInt(getArg(command, 1, "0"));
        Direction direction = Direction.parse(getArg(command, 2, "North"), Direction.North);
        String name = getArg(command, 3, null);
        if (name == null) {
            name = MobileRobot.class.getSimpleName() + (_NEXT_ID++);
        }
        MobileRobot robot = new MobileRobot(name);
        robot.setDirection(direction);
        Scene scene = World.THE_WORLD.currentScene();
        Grid grid = scene.grid();
        grid.addEntity(x, y, robot);
        _registerCommands(robot);
        return new Result.Success<Void, String>();
    }

    protected void _registerCommands(final MobileRobot robot) {
        CommandHandlerRegistry_deprecated registry = CommandHandlerRegistry_deprecated.THE_REGISTRY;
        String name = robot.name;
        registry.register(new MoveForward(robot, name, "move", "forward"));
        registry.register(new MoveBackward(robot, name, "move", "backward"));
        registry.register(new RotateRight(robot, name, "rotate", "right"));
        registry.register(new RotateLeft(robot, name, "rotate", "left"));
        registry.register(new PowerOn(robot, name, "power", "on"));
        registry.register(new PowerOff(robot, name, "power", "off"));
    }
}
