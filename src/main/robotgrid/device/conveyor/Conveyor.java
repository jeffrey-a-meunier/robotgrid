package robotgrid.device.conveyor;

import java.util.Optional;

import robotgrid.device.device.Device;
import robotgrid.device.poweredDevice.PoweredDevice;
import robotgrid.scene.Cell;

public class Conveyor extends PoweredDevice {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Conveyor(final String name) {
        super(name, 1);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    @Override
    public boolean addContent(final Device payload) {
        return super.addContent(payload);
    }

    public void reverse() {
        setHeading(_heading.opposite());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Optional<Device> payload_opt = peekContent();
            if (payload_opt.isPresent()) {
                delay();
                Cell adjacentCell = cell().getAdjacent(_heading);
                if (adjacentCell.addContent(payload_opt.get())) {
                    removeContent();
                }
            }
        }
        _isOn = false;
    }

    @Override
    public String typeName() {
        return "Conveyor";
    }

}
