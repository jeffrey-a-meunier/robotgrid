package robotgrid.device.rotatingBase;

import java.util.Optional;

import robotgrid.device.Device;
import robotgrid.device.PoweredDevice;

public class RotatingBase extends PoweredDevice {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public RotatingBase(final String name) {
        super(name, 1);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    @Override
    public void rotateLeft() {
        delay();
        Optional<Device> payload_opt = peekContent();
        if (payload_opt.isPresent()) {
            Device payload = payload_opt.get();
            payload.rotateLeft_immediate();
        }
    }

    @Override
    public void rotateRight() {
        delay();
        Optional<Device> payload_opt = peekContent();
        if (payload_opt.isPresent()) {
            Device payload = payload_opt.get();
            payload.rotateRight_immediate();
        }
    }

    @Override
    public String typeName() {
        return "RotatingBase";
    }

}
