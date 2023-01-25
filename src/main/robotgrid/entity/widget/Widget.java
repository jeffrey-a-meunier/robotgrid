package robotgrid.entity.widget;

import robotgrid.entity.Entity;
import robotgrid.entity.Height;
import robotgrid.graphics.Graphics;
import robotgrid.shape.Shape;

public abstract class Widget extends Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected final Shape _shape;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Widget(final String name, final Shape shape) {
        super(name);
        _height = Height.None;
        _shape = shape;
    }

    public Widget setFillColor(final int color) {
        _shape.setFillColor(color);
        return this;
    }

    // Instance methods =======================================================

    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        _shape.draw(graphics.layer(layerNum));
    }

}
