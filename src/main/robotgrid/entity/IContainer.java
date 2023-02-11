package robotgrid.entity;

import java.util.Optional;

public interface IContainer {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public boolean addPayload(final Entity entity);
    public int payloadCount();
    public Optional<Entity> peekPayload();
    public Optional<Entity> removePayload();

}
