package robotgrid.scene;

import processing.core.PApplet;
import robotgrid.graphics.Graphics;

public class Scene {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Grid _grid;
    protected Graphics _graphics;

    protected int _lastMouseX = -1;
    protected int _lastMouseY = -1;
    
    protected int _lastKeyPressed = -1;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Scene(final PApplet applet) {
        _graphics = new Graphics(applet);
    }

    // Instance methods =======================================================

    public void setGrid(final Grid grid) {
        _grid = grid;
    }

    public void draw(final PApplet applet) {
        applet.background(0, 0, 0);
        if (_grid != null) {
            _grid.draw(_graphics);
            _graphics.drawAllLayers(0, 0);
        }
    }

}
