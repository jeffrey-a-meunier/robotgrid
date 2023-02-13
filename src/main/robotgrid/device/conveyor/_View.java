package robotgrid.device.conveyor;

import processing.core.PGraphics;
import robotgrid.device.Device;
import robotgrid.device.View;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;

class _View extends View {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public _View(final Device device) {
        super(device);
    }

    // Instance methods =======================================================

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        PGraphics layer = graphics.layer(layerNum);
        float pos = -Cell.SIZE / 2.0f;
        layer.rect(pos, pos, Cell.SIZE, Cell.SIZE);
        _drawLines(layer);
        _drawPayload(graphics, layerNum + 1);
    }

    protected void _drawLines(final PGraphics layer) {
        float x0 = Cell.SIZE / 2.0f;
        float x1 = -x0;
        float dy6 = Cell.SIZE / 6.0f;
        float y0 = -Cell.SIZE / 2.0f;
        float y1 = y0 + dy6;
        for (int n=0; n<6; n++) {
            layer.line(x0, y1, 0, y0);
            layer.line(x1, y1, 0, y0);
            y0 = y1;
            y1 += dy6;
        }
    }

}
