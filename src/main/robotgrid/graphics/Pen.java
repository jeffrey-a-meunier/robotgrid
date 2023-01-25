package robotgrid.graphics;

import processing.core.PGraphics;

public class Pen {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _fillColor = 0xFF_FF_FF_FF;
    protected int _lineColor = 0xFF_00_00_00;
    protected float _lineWidth = 1.0f;

    // Instance initializer ===================================================
    // Constructors ===========================================================
    
    public Pen() {}

    public Pen(final int fillColor, final int lineColor, final float lineWidth) {
        _fillColor = fillColor;
        _lineColor = lineColor;
        _lineWidth = lineWidth;
    }

    public Pen setFillColor(final int fillColor) {
        _fillColor = fillColor;
        return this;
    }

    public Pen setLineColor(final int lineColor) {
        _lineColor = lineColor;
        return this;
    }

    public Pen setLineWidth(final float lineWidth) {
        _lineWidth = lineWidth;
        return this;
    }

    // Instance methods =======================================================t

    public void applyTo(final PGraphics graphics) {
        graphics.fill(_fillColor);
        graphics.stroke(_lineColor);
        graphics.strokeWeight(_lineWidth);
    }

}
