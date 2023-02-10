package robotgrid.entity.group;

import robotgrid.entity.PoweredEntity;

public class Group extends PoweredEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Group(final String name) {
        super(name, 0);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    @Override
    public String typeName() {
        return "Group";
    }

}
