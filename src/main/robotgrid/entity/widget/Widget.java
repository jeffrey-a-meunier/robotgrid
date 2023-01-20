package robotgrid.entity.widget;

import processing.core.PApplet;
import robotgrid.entity.Entity;
import robotgrid.scene.Color;
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
        _shape = shape;
    }

    // Instance methods =======================================================

    public void draw_aux(final PApplet applet) {
        _shape.draw(applet);
    }

    public Widget setFillColor(final int color) {
        _shape.setFillColor(new Color(color));
        return this;
    }

}
