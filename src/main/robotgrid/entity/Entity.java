package robotgrid.entity;

import processing.core.PApplet;
import robotgrid.scene.Cell;

public abstract class Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;
    protected Cell _cell = null;

    // These are values for drawing the Entity in the graphics window.
    protected float _x = 0.0f;
    protected float _y = 0.0f;
    protected float _angle = 0.0f;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Entity(final String name) {
        _name = name;
    }

    // Instance methods =======================================================

    public abstract void draw_aux(final PApplet applet);

    public void draw(final PApplet applet) {
        applet.pushMatrix();
        applet.translate(_x, _y);
        applet.rotate(_angle);
        draw_aux(applet);
        applet.popMatrix();
    }

    public Cell getCell() {
        return _cell;
    }

    public Entity setCell(final Cell cell) {
        _cell = cell;
        return this;
    }

    public void setXY(final float x, final float y) {
        _x = x;
        _y = y;
    }

    @Override
    public String toString() {
        return _name;
    }

}
