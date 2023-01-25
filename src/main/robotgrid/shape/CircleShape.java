package robotgrid.shape;

import processing.core.PGraphics;

public class CircleShape extends Shape {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected float _diameter;

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public CircleShape(final float diameter) {
        _diameter = diameter;
    }

    @Override
    public void draw(final PGraphics graphics) {
        super.draw(graphics);
        graphics.circle(0f, 0f, _diameter);
    }

}
