package robotgrid.entity.drone;

import java.util.Optional;

import robotgrid.entity.Entity;
import robotgrid.entity.PoweredEntity;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.scene.Scene;

public class Drone extends PoweredEntity {

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
        synchronized (_payload) {
            if (_payload.size() < _maxPayload) {
                Cell cell = cellBelow();
                Optional<Entity> payload_opt = cell.peekPayload();
                if (payload_opt.isPresent()) {
                    if (addPayload(payload_opt.get())) {
                        cell.removePayload();
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
                Entity payload = peekPayload().get();
                if (cell.addPayload(payload)) {
                    removePayload();
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
        Scene scene = cell().grid().scene();
        scene.moveFromGroundToAir(this);
    }

    @Override
    public void powerOff() {
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
