package robotgrid.scene;

import processing.core.PApplet;

public class Scene {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Grid _grid;

    protected int _lastMouseX = -1;
    protected int _lastMouseY = -1;
    
    protected int _lastKeyPressed = -1;

    // Instance initializer ===================================================
    // Constructors ===========================================================
    // Instance methods =======================================================

    public void setGrid(final Grid grid) {
        _grid = grid;
    }

    public void draw(final PApplet applet) {
        drawBackground(applet);
        if (_grid != null) {
            _grid.draw(applet, 0, 0);
        }
    }

    public void drawBackground(final PApplet applet) {
        applet.background(0, 0, 0);
    }

}
