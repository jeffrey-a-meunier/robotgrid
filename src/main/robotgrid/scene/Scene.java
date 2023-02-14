package robotgrid.scene;

import processing.core.PApplet;
import robotgrid.device.device.Device;
import robotgrid.graphics.Graphics;

public class Scene {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Grid _groundGrid;
    protected Grid _airGrid;
    protected Graphics _graphics;

    protected int _lastMouseX = -1;
    protected int _lastMouseY = -1;
    
    protected int _lastKeyPressed = -1;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Scene(final PApplet applet) {
        _graphics = new Graphics(applet);
    }

    public void setGroundGrid(final Grid grid) {
        _groundGrid = grid;
        _groundGrid.setLayerType(Grid.LayerType.Ground);
    }

    public void setAirGrid(final Grid grid) {
        _airGrid = grid;
        _airGrid.setLayerType(Grid.LayerType.Air);
    }

    // Instance methods =======================================================

    public Grid airGrid() {
        return _airGrid;
    }

    public Grid groundGrid() {
        return _groundGrid;
    }

    public void draw(final PApplet applet) {
        applet.background(0, 0, 0);
        if (_groundGrid != null) {
            _groundGrid.draw(_graphics);
        }
        if (_airGrid != null) {
            _airGrid.draw(_graphics);
        }
        _graphics.drawAllLayers(0, 0);
    }

    public Cell cellBelow(final Cell airCell) {
        int rowNum = airCell.row();
        int colNum = airCell.col();
        return _groundGrid.getCell(rowNum, colNum);
    }

    public void moveFromAirToGround(final Device device) {
        Cell airCell = device.cell();
        int rowNum = airCell.row();
        int colNum = airCell.col();
        Cell groundCell = _groundGrid.getEmptyCellNear(rowNum, colNum);
        airCell.removeDevice(device);
        groundCell.addContent(device);
    }

    public void moveFromGroundToAir(final Device device) {
        Cell groundCell = device.cell();
        int rowNum = groundCell.row();
        int colNum = groundCell.col();
        Cell airCell = _airGrid.getCell(rowNum, colNum);
        groundCell.removeDevice(device);
        airCell.addContent(device);
    }

}
