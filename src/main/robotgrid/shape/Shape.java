package robotgrid.shape;

import processing.core.PGraphics;
import robotgrid.graphics.Pen;

public abstract class Shape {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static final int _FILL_COLOR = 0xFF_FF_FF_FF;
    protected static final int _LINE_COLOR = 0xFF_00_00_00;
    protected static final float _LINE_WIDTH = 1;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Pen _pen = new Pen(_FILL_COLOR, _LINE_COLOR, _LINE_WIDTH);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Shape() {}

    // Instance methods =======================================================

    /**
     * Subclasses should override, and also invoke this as super(graphics).
     */
    public void draw(final PGraphics graphics) {
        _pen.applyTo(graphics);
    }

    public void setFillColor(final int color) {
        _pen.setFillColor(color);
    }

    public void setLineColor(final int color) {
        _pen.setLineColor(color);
    }

}
