package robotgrid.entity.group;

import robotgrid.entity.abstractEntity.AbstractEntity;

public class Group extends AbstractEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Group(final String name) {
        super(name);
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    @Override
    public String typeName() {
        return "Group";
    }

}
