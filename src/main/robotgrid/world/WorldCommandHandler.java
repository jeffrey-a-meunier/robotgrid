package robotgrid.world;

import java.util.List;

import robotgrid.entity.Entity;
import robotgrid.world.commands.Exit;
import robotgrid.world.commands.Entities;
import robotgrid.world.commands.NewArm;
import robotgrid.world.commands.NewConveyor;
import robotgrid.world.commands.NewGroup;
import robotgrid.world.commands.NewMovingBase;
import robotgrid.world.commands.NewRotatingBase;
import robotgrid.world.commands.NewTable;
import robotgrid.world.commands.NewWidget;

/**
 * The World class is already a subclass of PApplet, so this class is used
 * as the command handling Entity.
 */
public class WorldCommandHandler extends Entity {

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
        addCommandHandler("Entities", new Entities());
        addCommandHandler("Exit", new Exit());
        addCommandHandler("NewArm", new NewArm());
        addCommandHandler("NewConveyor", new NewConveyor());
        addCommandHandler("NewGroup", new NewGroup());
        addCommandHandler("NewMovingBase", new NewMovingBase());
        addCommandHandler("NewRotatingBase", new NewRotatingBase());
        addCommandHandler("NewTable", new NewTable());
        addCommandHandler("NewWidget", new NewWidget());
    }

    // Instance methods =======================================================

    @Override
    public void info(final List<String> strings) {
        strings.add("Type=" + getClass().getSimpleName());
        List<String> names = Entity.names();
        strings.add("Entities=" + names);
        strings.add("Count=" + names.size());
    }

    @Override
    public String typeName() {
        return "World";
    }

}
