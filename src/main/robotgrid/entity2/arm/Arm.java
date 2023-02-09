package robotgrid.entity2.arm;

import robotgrid.entity2.Entity2;
import robotgrid.scene.Cell;

public class Arm extends Entity2 {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected boolean _isExtended = false;
    protected boolean _isGripping = false;

    // Instance initializer ===================================================
    // Constructors ===========================================================


    public Arm(final String name) {
        super(name);
        setView(new _View(this));
        _Commands.setup(this);
    }

    // Instance methods =======================================================

    public void extend() {
        _isExtended = true;
    }

    public void retract() {
        _isExtended = false;
    }

    public boolean isExtended() {
        return _isExtended;
    }

    public void grip() {
        delay();
        if (_isExtended && !_isGripping) {
            if(_payload == null) {
                Cell adjacentCell = _cell.getAdjacent(_heading);
                Entity2 payload = adjacentCell.removePayload();
                if (payload != null) {
                    addPayload(payload);
                }
            }
        }
        _isGripping = true;
    }

    public void release() {
        delay();
        if (_isExtended && _payload != null) {
            Cell adjacentCell = _cell.getAdjacent(_heading);
            if (adjacentCell.add(_payload)) {
                _payload = null;
                _isGripping = false;
            }
        }
    }

    public boolean isGripping() {
        return _isGripping;
    }

}
