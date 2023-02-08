package robotgrid.world;

import robotgrid.entity2.Entity2;
import robotgrid.world.commands.Exit;
import robotgrid.world.commands.ListEntities;
import robotgrid.world.commands.NewMovingBase;

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
        addCommandHandler("NewMovingBase", new NewMovingBase());
    }

    // Instance methods =======================================================

}
