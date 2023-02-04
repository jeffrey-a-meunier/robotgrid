package robotgrid.server.commands.articulatedrobot;

import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;
import robotgrid.server.Command;
import robotgrid.server.CommandHandler;
import robotgrid.server.CommandHandlerRegistry;
import robotgrid.utils.Result;
import robotgrid.world.World;

public class CreateArticulatedRobot extends CommandHandler {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_ID = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public CreateArticulatedRobot(final String ... commandParts) {
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
            name = ArticulatedRobot.class.getSimpleName() + (_NEXT_ID++);
        }
        ArticulatedRobot robot = new ArticulatedRobot(name);
        robot.setDirection(direction);
        Scene scene = World.THE_WORLD.currentScene();
        Grid grid = scene.grid();
        grid.addEntity(x, y, robot);
        _registerCommands(robot);
        return new Result.Success<Void, String>();
    }

    protected void _registerCommands(final ArticulatedRobot robot) {
        CommandHandlerRegistry registry = CommandHandlerRegistry.THE_REGISTRY;
        String name = robot.name;
        registry.register(new ArmExtend(robot, name, "arm", "extend"));
        registry.register(new ArmRetract(robot, name, "arm", "retract"));
        registry.register(new GripperGrip(robot, name, "gripper", "grip"));
        registry.register(new GripperRelease(robot, name, "gripper", "release"));
        registry.register(new RotateRight(robot, name, "rotate", "right"));
        registry.register(new RotateLeft(robot, name, "rotate", "left"));
        registry.register(new PowerOn(robot, name, "power", "on"));
        registry.register(new PowerOff(robot, name, "power", "off"));
    }

}
