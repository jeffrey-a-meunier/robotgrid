package robotgrid.scene;

import robotgrid.device.device.Device;
import robotgrid.device.widget.Widget;
import robotgrid.graphics.Graphics;

public class AirCell extends Cell {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public AirCell(Grid grid, int rowNum, int colNum) {
        super(grid, rowNum, colNum);
    }

    // Instance methods =======================================================

    public synchronized boolean addContent(final Device device) {
        if (device instanceof Widget) {
            Cell cell = cellBelow();
            return cell.addContent(device);
        }
        return super.addContent(device);
    }

    protected Cell cellBelow() {
        return grid().scene().cellBelow(this);
    }
    

    public void draw(final Graphics graphics) {
        if (_device != null) {
            _device.draw(graphics, 1);
        }
    }

}
