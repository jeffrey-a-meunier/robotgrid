package robotgrid.device;

import java.util.Optional;

import robotgrid.device.device.Device;
import robotgrid.scene.Grid;

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

    public int contentCount();
    public boolean addContent(final Device payload);
    public Optional<Device> peekContent();
    public Optional<Device> removeContent();
    public default Optional<Device> removeContent(final Device payload) {
        return removeContent();
    }
    public Grid.LayerType layerType();

}
