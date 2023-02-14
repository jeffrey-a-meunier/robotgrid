package robotgrid.scene;

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

    public void draw(final Graphics graphics) {
        if (_device != null) {
            _device.draw(graphics, 1);
        }
    }

}
