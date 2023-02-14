package robotgrid.scene;

import processing.core.PApplet;
import robotgrid.device.device.Device;
import robotgrid.graphics.Graphics;
import robotgrid.utils.Logger;

public abstract class Grid {

    // Static inner classes ===================================================

    public static enum LayerType {
        Ground("Ground", 0), Air("Air", 5);
        public final String name;
        public final int graphicsLayer;
        private LayerType(final String name, final int graphicsLayer) {
            this.name = name;
            this.graphicsLayer = graphicsLayer;
        }
    }

    // Static variables =======================================================

    protected static int _NEXT_INDEX = 0;

    private static Logger _LOGGER = new Logger(Grid.class);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;
    protected int _nRows;
    protected int _nCols;
    protected int _cellWidth;
    protected int _cellHeight;

    protected Cell[][] _cells;
    protected Scene _scene;
    protected LayerType _layerType;

    protected Graphics _graphics;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public abstract Cell createCell(final Grid grid, final int rowNum, final int colNum);

    public Grid(final Scene scene, final int nRows, final int nCols, int cellWidth, int cellHeight) {
        _scene = scene;
        _name = "Grid-" + (_NEXT_INDEX++);
        _nRows = nRows;
        _nCols = nCols;
        _cellWidth = cellWidth;
        _cellHeight = cellHeight;
        _cells = new Cell[_nRows][_nCols];
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            for (int colNum=0; colNum < _nCols; colNum++) {
                _cells[rowNum][colNum] = createCell(this, rowNum, colNum);
            }
        }
    }

    public Grid setLayerType(final LayerType layerType) {
        _layerType = layerType;
        return this;
    }

    public Grid createGraphics(final PApplet applet) {
        _graphics = new Graphics(applet);
        return this;
    }

    // Instance methods =======================================================

    public boolean addDevice(final int rowNum, final int colNum, final Device device) {
        Cell cell = getCell(rowNum, colNum);
        if (cell != null) {
            return cell.addContent(device);
        }
        return false;
    }

    public void draw() {
        float y = Cell.SIZE2;
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            float x = Cell.SIZE2;
            for (int colNum=0; colNum < _nCols; colNum++) {
                _graphics.resetMatrix().translate(x, y);
                _cells[rowNum][colNum].draw(_graphics);
                x += Cell.SIZE;
            }
            y += Cell.SIZE;
        }
    }

    public Cell getCell(final int rowNum, final int colNum) {
        if (rowNum < 0 || rowNum >= _nRows || colNum < 0 || colNum >= _nCols) {
            return null;
        }
        return _cells[rowNum][colNum];
    }

    public Cell getEmptyCellNear(final int rowNum, final int colNum) {
        Cell cell = _cells[rowNum][colNum];
        if (cell.peekContent().isEmpty()) {
            return cell;
        }
        // TODO finish this
        _LOGGER.error("getEmptyCellNear() is incomplete");
        return null;
    }

    public Graphics graphics() {
        return _graphics;
    }

    public LayerType layerType() {
        return _layerType;
    }

    public void move(final Device device, final Direction direction) {
        Cell cell = device.cell();
        Cell nextCell = cell.getAdjacent(direction);
        if (nextCell != null) {
            if (nextCell.addContent(device)) {
                cell.removeDevice(device);
            }
        }
    }

    public String name() {
        return _name;
    }

    public Scene scene() {
        return _scene;
    }

}
