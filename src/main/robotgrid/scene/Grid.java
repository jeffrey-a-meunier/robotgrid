package robotgrid.scene;

import robotgrid.entity.Entity;
import robotgrid.graphics.Graphics;

public class Grid {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static int _NEXT_INDEX = 0;

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

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Grid(final int nRows, final int nCols, int cellWidth, int cellHeight) {
        _name = "Grid-" + (_NEXT_INDEX++);
        _nRows = nRows;
        _nCols = nCols;
        _cellWidth = cellWidth;
        _cellHeight = cellHeight;
        _cells = new Cell[_nRows][_nCols];
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            for (int colNum=0; colNum < _nCols; colNum++) {
                _cells[rowNum][colNum] = new Cell(this, rowNum, colNum);
            }
        }
    }

    // Instance methods =======================================================

    public boolean addEntity(final int rowNum, final int colNum, final Entity entity) {
        Cell cell = getCell(rowNum, colNum);
        if (cell != null) {
            return cell.addPayload(entity);
        }
        return false;
    }

    public void draw(final Graphics graphics) {
        float y = Cell.SIZE2;
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            float x = Cell.SIZE2;
            for (int colNum=0; colNum < _nCols; colNum++) {
                graphics.resetMatrix().translate(x, y);
                _cells[rowNum][colNum].draw(graphics);
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

    public void move(final Entity entity, final Direction direction) {
        Cell cell = entity.cell();
        Cell nextCell = cell.getAdjacent(direction);
        if (nextCell != null) {
            if (nextCell.addPayload(entity)) {
                cell.removePayload();
            }
        }
    }

    public String name() {
        return _name;
    }

}
