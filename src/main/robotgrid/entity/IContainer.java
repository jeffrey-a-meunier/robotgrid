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

    public int payloadCount();
    public boolean addPayload(final Entity payload);
    public Optional<Entity> peekPayload();
    public Optional<Entity> removePayload();

}
