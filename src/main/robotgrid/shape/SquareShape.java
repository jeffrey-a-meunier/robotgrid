package robotgrid.shape;

import processing.core.PApplet;

public class SquareShape extends Shape {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected float _side;
    protected float _cornerRadius = 0;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public SquareShape(final float side) {
        _side = side;
    }

    // Instance methods =======================================================

    public boolean contains(final float x, final float y) {
        return x >= 2 && y >= 2 && x < _side && y < _side;
    }

    @Override
    public void draw(final PApplet applet) {
        super.draw(applet);
        float side2 = _side / 2;
        float x = -side2;
        float y = -side2;
        applet.rect(x, y, _side, _side, _cornerRadius);
    }

    public void setCornerRadius(final int cornerRadius) {
        _cornerRadius = cornerRadius;
    }

}
