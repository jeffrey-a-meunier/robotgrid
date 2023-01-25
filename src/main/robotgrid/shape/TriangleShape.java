package robotgrid.shape;

import processing.core.PGraphics;

public class TriangleShape extends Shape {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected float _size;
    protected float _x1;
    protected float _y1;
    protected float _x2;
    protected float _y2;
    protected float _x3;
    protected float _y3;

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    /**
     * This creates the triangle with the offset at 0, 0.
     */
    public TriangleShape(final float size) {
        this(0, size/2.0f, size);
    }

    /**
     * This creates the triangle with the center at offset x, y.
     */
    public TriangleShape(final float x, final float y, final float size) {
        _size = size;
        float size2 = _size / 2;
        // x1 y1 are the tip
        _x1 = x;
        _y1 = y - size2;
        // x2 y2 are the right corner
        _x2 = _x1 + size2;
        _y2 = _y1 + size;
        // x3 y3 are the left corner
        _x3 = _x1 - size2;
        _y3 = _y2;
    }

    @Override
    public void draw(final PGraphics graphics) {
        super.draw(graphics);
        graphics.triangle(_x1, _y1, _x2, _y2, _x3, _y3);
    }

}
