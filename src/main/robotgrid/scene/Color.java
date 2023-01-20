package robotgrid.scene;

import processing.core.PApplet;

public class Color {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _color;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Color(final int color) {
        _color = color;
    }

    // Instance methods =======================================================

    public void applyFill(final PApplet applet) {
        applet.fill(_color);
    }

    public void applyStroke(final PApplet applet) {
        applet.stroke(_color);
    }

}
