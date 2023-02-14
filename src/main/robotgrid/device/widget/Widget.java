package robotgrid.device.widget;

import java.util.Optional;

import robotgrid.device.Device;
import robotgrid.shape.Shape;

public abstract class Widget extends Device {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Widget(final String name, final Shape shape) {
        super(name);
        setView(new _View(this, shape));
    }

    // Instance methods =======================================================

    @Override
    public boolean addContent(final Device payload) {
        return false;
    }

    @Override
    public Optional<Device> removeContent() {
        return Optional.of(this);
    }

}
