package robotgrid.device.drone;

import java.util.Optional;

import robotgrid.device.device.Device;
import robotgrid.device.poweredDevice.PoweredDevice;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.scene.Grid;
import robotgrid.scene.Scene;

public class Drone extends PoweredDevice {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Drone(String name) {
        super(name, 4);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    public void pickUp() {
        delay();
        Cell cell = cell();
        if (cell.layerType() == Grid.LayerType.Ground) {
            return;
        }
        synchronized (_payload) {
            if (_payload.size() < _maxPayload) {
                Cell cellBelow = cellBelow();
                Optional<Device> payload_opt = cellBelow.peekContent();
                if (payload_opt.isPresent()) {
                    if (addContent(payload_opt.get())) {
                        cellBelow.removeContent();
                    }
                }
            }
        }
    }

    public void drop() {
        delay();
        synchronized (_payload) {
            if (_payload.size() > 0) {
                Cell cell = cellBelow();
                Device payload = peekContent().get();
                if (cell.addContent(payload)) {
                    removeContent();
                }
            }
        }
    }

    public void move(final Direction direction) {
        delay();
        cell().grid().move(this, direction);
    }

    @Override
    public void powerOn() {
        if (_isOn) {
            return;
        }
        super.powerOn();
        delay();
        Scene scene = cell().grid().scene();
        scene.moveFromGroundToAir(this);
    }

    @Override
    public void powerOff() {
        delay();
        super.powerOff();
        Scene scene = cell().grid().scene();
        scene.moveFromAirToGround(this);
    }

    @Override
    public String typeName() {
        return "Drone";
    }

    protected Cell cellBelow() {
        return cell().grid().scene().cellBelow(cell());
    }

}
