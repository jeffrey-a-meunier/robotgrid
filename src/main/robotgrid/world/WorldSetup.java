package robotgrid.world;

import robotgrid.entity.active.controller.ControllerGroup;
import robotgrid.entity.active.conveyor.Conveyor;
import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.entity.widget.SquareWidget;
import robotgrid.entity.widget.Widget;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;

@Deprecated  // TODO read a JSON file for setup instructions
public class WorldSetup {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NCOLS = 10;
    protected static int _NROWS = 10;

    // Static initializer =====================================================

    static {
        _NCOLS = 10;
        _NROWS = 10;
        //Cell.setSize(80.0f);
    }

    // Static methods =========================================================

    public static int getWorldHeight() {
        return (int)Cell.SIZE * _NROWS;
    }

    public static int getWorldWidth_() {
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
        ArticulatedRobot robot2 = (ArticulatedRobot)new ArticulatedRobot("ArticulatedRobot1")
            .setDirection(Direction.North)
            ;
        grid1.addEntity(4, 5, robot2);

        ControllerGroup conveyorGroup1 = new ControllerGroup("ConveyorGroup1");
        Conveyor[] conveyors = new Conveyor[4];
        for (int n=0; n<4; n++) {
            Conveyor conveyor = (Conveyor)new Conveyor("Conveyor" + n)
                .setDirection(Direction.West)
                ;
            grid1.addEntity(5, 5 - n, conveyor);
            conveyorGroup1.add(conveyor);
            conveyors[n] = conveyor;
            // conveyors[n].powerOn();
        }
        conveyorGroup1.powerOn();

        // Send some instructions to the robots

        String[] program1 = {
            "MoveForward",
            "MoveForward",
            "RotateRight",
            "MoveForward",
            "MoveForward"
        };
        // robot1.controller().sendCommands(program1);
        robot1.powerOn();

        String[] program2 = {
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
        robot2.controller().sendCommands(program2);
        robot2.powerOn();
    }

}
