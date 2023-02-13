package robotgrid.device.arm;

import java.util.Optional;

import robotgrid.device.Device;
import robotgrid.device.PoweredDevice;
import robotgrid.scene.Cell;

public class Arm extends PoweredDevice {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected boolean _isExtended = false;
    protected boolean _isGrippingPayload = false;

    // Instance initializer ===================================================
    // Constructors ===========================================================


    public Arm(final String name) {
        super(name, 1);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    public void extend() {
        delay();
        _isExtended = true;
    }

    public void retract() {
        delay();
        _isExtended = false;
    }

    public boolean isExtended() {
        return _isExtended;
    }

    public void grip() {
        delay();
        if (_isExtended && !_isGrippingPayload) {
            if(_payload.size() == 0) {
                Cell adjacentCell = cell().getAdjacent(_heading);
                Optional<Device> payload_opt = adjacentCell.removePayload();
                if (payload_opt.isPresent()) {
                    addPayload(payload_opt.get());
                }
            }
        }
        _isGrippingPayload = true;
    }

    public void release() {
        delay();
        if (_isExtended) {
            Cell adjacentCell = cell().getAdjacent(_heading);
            Optional<Device> payload_opt = removePayload();
            if (payload_opt.isPresent()) {
                adjacentCell.addPayload(payload_opt.get());
            }
        }
        _isGrippingPayload = false;
    }

    public boolean isGripping() {
        return _isGrippingPayload;
    }

    @Override
    public String typeName() {
        return "Arm";
    }

}
