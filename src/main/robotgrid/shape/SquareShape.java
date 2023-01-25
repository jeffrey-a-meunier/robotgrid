package robotgrid.shape;

import processing.core.PGraphics;

public class SquareShape extends Shape {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected float _side, _side2;
    protected float _x, _y;
    protected float _cornerRadius = 0;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public SquareShape(final float side) {
        _side = side;
        _side2 = side / 2;
        _x = -_side2;
        _y = -_side2;
    }

    // Instance methods =======================================================

    @Override
    public void draw(final PGraphics graphics) {
        super.draw(graphics);
        graphics.rect(_x, _y, _side, _side, _cornerRadius);
    }

    public void setCornerRadius(final int cornerRadius) {
        _cornerRadius = cornerRadius;
    }

}
