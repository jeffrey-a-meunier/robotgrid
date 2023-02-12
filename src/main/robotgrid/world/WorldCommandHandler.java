package robotgrid.world;

import java.util.List;

import robotgrid.entity.Entity;
import robotgrid.entity.abstractEntity.AbstractEntity;
import robotgrid.world.commands.Exit;
import robotgrid.world.commands.Entities;
import robotgrid.world.commands.CellInfo;
import robotgrid.world.commands.CreateArm;
import robotgrid.world.commands.CreateConveyor;
import robotgrid.world.commands.CreateDrone;
import robotgrid.world.commands.CreateGroup;
import robotgrid.world.commands.CreateMovingBase;
import robotgrid.world.commands.CreateRotatingBase;
import robotgrid.world.commands.CreateTable;
import robotgrid.world.commands.CreateWidget;

/**
 * The World class is already a subclass of PApplet, so this class is used
 * as the command handling Entity.
 */
public class WorldCommandHandler extends AbstractEntity {

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
        addCommandHandler("CellInfo", new CellInfo());
        addCommandHandler("CreateArm", new CreateArm());
        addCommandHandler("CreateConveyor", new CreateConveyor());
        addCommandHandler("CreateDrone", new CreateDrone());
        addCommandHandler("CreateGroup", new CreateGroup());
        addCommandHandler("CreateMovingBase", new CreateMovingBase());
        addCommandHandler("CreateRotatingBase", new CreateRotatingBase());
        addCommandHandler("CreateTable", new CreateTable());
        addCommandHandler("CreateWidget", new CreateWidget());
    }

    // Instance methods =======================================================

    @Override
    public void info(final List<String> strings) {
        super.info(strings);
        List<String> names = Entity.names();
        strings.add("Entities=" + names);
        strings.add("Count=" + names.size());
    }

    @Override
    public String typeName() {
        return "World";
    }

}
