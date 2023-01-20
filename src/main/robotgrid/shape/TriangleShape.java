package robotgrid.shape;

import processing.core.PApplet;

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

    public TriangleShape(final float size) {
        setSize(size);
    }

    @Override
    public void draw(final PApplet applet) {
        super.draw(applet);
        applet.triangle(_x1, _y1, _x2, _y2, _x3, _y3);
    }

    public void setSize(final float size) {
        _size = size;
        float size2 = _size / 2;
        _x1 = 0;
        _y1 = -size2;
        _x2 = size2;
        _y2 = size2;
        _x3 = -size2;
        _y3 = size2;
    }

}
