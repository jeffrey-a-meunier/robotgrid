package robotgrid.scene;

import robotgrid.graphics.Graphics;

public class GroundCell extends Cell {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public GroundCell(Grid grid, int rowNum, int colNum) {
        super(grid, rowNum, colNum);
    }

    // Instance methods =======================================================

    public void draw(final Graphics graphics) {
        _drawBackground(graphics.layer(0));
        if (_device != null) {
            _device.draw(graphics, 1);
        }
    }

}
