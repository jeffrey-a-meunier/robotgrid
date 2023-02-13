package robotgrid.device;

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
    public boolean addPayload(final Device payload);
    public Optional<Device> peekPayload();
    public Optional<Device> removePayload();
    public default Optional<Device> removePayload(final Device payload) {
        return removePayload();
    }

}
