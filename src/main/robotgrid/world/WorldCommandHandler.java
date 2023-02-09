package robotgrid.world;

import robotgrid.entity2.Entity2;
import robotgrid.world.commands.Exit;
import robotgrid.world.commands.ListEntities;
import robotgrid.world.commands.NewArm;
import robotgrid.world.commands.NewConveyor;
import robotgrid.world.commands.NewMovingBase;
import robotgrid.world.commands.NewRotatingBase;
import robotgrid.world.commands.NewTable;
import robotgrid.world.commands.NewWidget;

/**
 * The World class is already a subclass of PApplet, so this class is used
 * as the command handling Entity.
 */
public class WorldCommandHandler extends Entity2 {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public WorldCommandHandler() {
        super("World");
        addCommandHandler("Exit", new Exit());
        addCommandHandler("ListEntities", new ListEntities());
        addCommandHandler("NewArm", new NewArm());
        addCommandHandler("NewConveyor", new NewConveyor());
        addCommandHandler("NewMovingBase", new NewMovingBase());
        addCommandHandler("NewRotatingBase", new NewRotatingBase());
        addCommandHandler("NewTable", new NewTable());
        addCommandHandler("NewWidget", new NewWidget());
    }

    // Instance methods =======================================================

}
