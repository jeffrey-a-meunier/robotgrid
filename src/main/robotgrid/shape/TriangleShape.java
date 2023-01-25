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
         _x1 = x;
        _y1 = y;
        _x2 = _x1 + size2;
        _y2 = _y1 + size;
        _x3 = _x1 - size2;
        _y3 = _y2;
    }

    @Override
    public void draw(final PGraphics graphics) {
        super.draw(graphics);
        graphics.triangle(_x1, _y1, _x2, _y2, _x3, _y3);
    }

}
