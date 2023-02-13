package robotgrid.device.rotatingBase;

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
        float size = Cell.SIZE * 0.9f;
        float pos = -size / 2.0f;
        layer.rect(pos, pos, size, size);
        layer.circle(0.0f, 0.0f, Cell.SIZE * 0.7f);
        _drawPayload(graphics, layerNum);
    }

}
