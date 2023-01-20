package robotgrid.shape;

import processing.core.PApplet;
import robotgrid.scene.Color;

public abstract class Shape {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected float _lineWidth = 1;
    protected Color _fillColor = new Color(0xFF_FF_FF_FF);
    protected Color _lineColor = new Color(0xFF_40_40_40);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Shape() {}

    // Instance methods =======================================================

    public void draw(final PApplet applet) {
        _fillColor.applyFill(applet);
        _lineColor.applyStroke(applet);
        applet.strokeWeight(_lineWidth);
    }

    public void setFillColor(final Color color) {
        _fillColor = color;
    }

    public void setLineColor(final Color color) {
        _lineColor = color;
    }

}
