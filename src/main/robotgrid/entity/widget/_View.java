package robotgrid.entity.widget;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.entity.View;
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

    public _View(final Entity entity, final Shape shape) {
        super(entity);
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
