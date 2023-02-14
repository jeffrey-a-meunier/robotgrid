package robotgrid.device.widget;

import processing.core.PGraphics;
import robotgrid.device.device.Device;
import robotgrid.device.device.View;
import robotgrid.graphics.Graphics;
import robotgrid.shape.Shape;

class _View extends View {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Shape _shape;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public _View(final Device device, final Shape shape) {
        super(device);
        _shape = shape;
    }

    // Instance methods =======================================================

        @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        PGraphics layer = graphics.layer(layerNum);
        _shape.draw(layer);
    }

}
