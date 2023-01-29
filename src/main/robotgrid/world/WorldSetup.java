package robotgrid.world;

import robotgrid.entity.active.conveyor.Conveyor;
import robotgrid.entity.active.conveyor.ConveyorController;
import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.entity.widget.SquareWidget;
import robotgrid.entity.widget.Widget;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;

public class WorldSetup {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NCOLS = 10;
    protected static int _NROWS = 10;

    // Static initializer =====================================================

    static {
        _NCOLS = 10;
        _NROWS = 10;
        Cell.setSize(80.0f);
    }

    // Static methods =========================================================

    public static int getWorldHeight() {
        return (int)Cell.SIZE * _NROWS;
    }

    public static int getWorldWidth() {
        return (int)Cell.SIZE * _NCOLS;
    }

    public static void setup(final World world) {
        Grid grid1 = new Grid(10, 10, 50, 50);
        Scene scene1 = new Scene(world);
        scene1.setGrid(grid1);
        String gridSceneName = "Grid 1";
        world.addScene(gridSceneName, scene1);
        world.setCurrentScene(gridSceneName);

        // mobile robot
        MobileRobot robot1 = (MobileRobot)new MobileRobot("MobileRobot1");
        grid1.addEntity(3, 5, robot1);

        Widget squareWidget1 = new SquareWidget("SquareWidghet1")
            .setFillColor(0xFF_FF_00_00);
        robot1.addPayload(squareWidget1);

        // articulated robot
//        ArticulatedRobot_old robot2 = (ArticulatedRobot_old)new ArticulatedRobot_old("ArticulatedRobot1")
        ArticulatedRobot robot2 = (ArticulatedRobot)new ArticulatedRobot("ArticulatedRobot1")
            .setDirection(Direction.North)
            ;
        grid1.addEntity(4, 5, robot2);

        ConveyorController conveyorController1 = new ConveyorController("Conveyor1");
        Conveyor[] conveyors = new Conveyor[4];
        for (int n=0; n<4; n++) {
            conveyors[n] = (Conveyor)new Conveyor("Conveyor" + n, conveyorController1)
                .setDirection(Direction.West)
                ;
            grid1.addEntity(5, 5 - n, conveyors[n]);
            conveyors[n].powerOn();
        }

        // Send some instructions to the robots

        // robot1.controller().sendCommands(
        //     "MoveForward",
        //     "MoveForward",
        //     "RotateRight",
        //     "MoveForward",
        //     "MoveForward"
        // );
        robot1.powerOn();

        String[] program1 = {
            "ArmExtend",
            "GripperGrip",
            "ArmRetract",
            "RotateRight",
            "RotateRight",
            "ArmExtend",
            "GripperRelease",
            "ArmRetract",
            "RotateLeft",
            "RotateLeft",
        };
        robot2.controller().sendCommands(program1);
        robot2.powerOn();
    }

}
