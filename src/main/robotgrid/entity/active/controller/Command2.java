package robotgrid.entity.active.controller;

import robotgrid.entity.active.ActiveEntity;
import robotgrid.utils.Result;
import robotgrid.utils.UID;

public abstract class Command2 {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public UID uid = new UID();

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public abstract Result<Void, String> execute(final ActiveEntity entity);

}
