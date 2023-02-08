package robotgrid.entity.active.conveyor;

import robotgrid.entity.active.controller.Controller;

public class ConveyorController extends Controller {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _latency = 1000;  // mS

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ConveyorController(final String name) {
        super(name);
    }

    // Instance methods =======================================================

    protected enum State {
        Sleeping
    }

    @Override
    public void run() {
        // _isOn = true;
        // assert _entity != null;
        // Conveyor conveyor = (Conveyor)_entity;
        // Direction direction = conveyor.direction();
        // Cell adjacentCell = conveyor.getCell().getAdjacent(direction);
        // while (!Thread.currentThread().isInterrupted()) {
        //     Entity payload = conveyor.payload();
        //     if (payload != null) {
        //         if (sleep(_latency)) {
        //             if (adjacentCell.add(payload)) {
        //                 conveyor.removePayload();
        //             }
        //         }
        //     }
        // }
        // _isOn = false;
    }

    protected boolean sleep(final int ms) {
        try {
            Thread.sleep(ms);
            return true;
        }
        catch (final InterruptedException exn) {
            return false;
        }
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + '{' + name + '}';
    }

}
