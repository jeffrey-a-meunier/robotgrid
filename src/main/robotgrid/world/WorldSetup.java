package robotgrid.world;

import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.entity.active.robot.MobileRobot;
import robotgrid.entity.widget.SquareWidget;
import robotgrid.entity.widget.Widget;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;

public class WorldSetup {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setup(final World world) {
        Grid grid1 = new Grid(10, 10, 50, 50);
        Scene scene1 = new Scene();
        scene1.setGrid(grid1);
        String gridSceneName = "Grid 1";
        world.addScene(gridSceneName, scene1);
        world.setCurrentScene(gridSceneName);

        // mobile robot
        MobileRobot robot1 = (MobileRobot)new MobileRobot("MobileRobot1");
        grid1.addEntity(3, 3, robot1);

        Widget squareWidget1 = new SquareWidget("SquareWidghet1")
            .setFillColor(0xFF_FF_00_00);
        robot1.addPayload(squareWidget1);

        // articulated robot
        ArticulatedRobot robot2 = (ArticulatedRobot)new ArticulatedRobot("ArticulatedRobot1");
        grid1.addEntity(4, 5, robot2);

        // Send some instructions to the robots

        robot1.controller()
            .sendMessage("MoveForward")
            .sendMessage("MoveForward")
            .sendMessage("RotateRight")
            .sendMessage("MoveForward")
            .sendMessage("MoveForward")
            ;
        robot1.powerOn();

        robot2.controller()
            .sendMessage("ArmExtend")
            .sendMessage("ArmRetract")
            .sendMessage("RotateRight")
            .sendMessage("ArmExtend")
            .sendMessage("ArmRetract")
            .sendMessage("RotateLeft")
            .sendMessage("ArmExtend")
            .sendMessage("ArmRetract")
            ;
        robot2.powerOn();
    }

}
