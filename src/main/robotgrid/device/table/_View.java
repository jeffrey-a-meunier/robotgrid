package robotgrid.device.table;

import java.util.List;

import processing.core.PGraphics;
import robotgrid.device.device.Device;
import robotgrid.device.device.View;
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
        _drawPayload(graphics, layerNum + 1);
    }

    @Override
    protected void _drawPayload(final Graphics graphics, final int layerNum) {
        List<Device> allContent = _device.allContent();
        float widgetSize = Cell.SIZE / 3.0f;
        float x = - widgetSize;
        float y = - widgetSize;
        graphics.translate(x, y);
        int colN = 0;
        for (Device device : allContent) {
            device.draw(graphics, layerNum);
            colN++;
            graphics.translate(widgetSize, 0.0f);
            if (colN == 3) {
                colN = 0;
                graphics.translate(-widgetSize*3.0f, widgetSize);
            }
        }
    }

}
