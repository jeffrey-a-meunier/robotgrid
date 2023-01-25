package robotgrid.scene;

import processing.core.PMatrix;
import processing.core.PMatrix2D;
import robotgrid.entity.Entity;
import robotgrid.graphics.Graphics;

public class Grid {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _nRows;
    protected int _nCols;
    protected int _cellWidth;
    protected int _cellHeight;

    protected Cell[][] _cells;
    protected PMatrix[][] _origins;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Grid(final int nRows, final int nCols, int cellWidth, int cellHeight) {
        _nRows = nRows;
        _nCols = nCols;
        _cellWidth = cellWidth;
        _cellHeight = cellHeight;
        _cells = new Cell[_nRows][_nCols];
        _origins = new PMatrix[_nRows][_nCols];
        float y = Cell.SIZE2;
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            float x = Cell.SIZE2;
            for (int colNum=0; colNum < _nCols; colNum++) {
                _cells[rowNum][colNum] = new Cell(this, rowNum, colNum);
                PMatrix2D matrix = new PMatrix2D();
                matrix.translate(x, y);
                _origins[rowNum][colNum] = matrix;
                x += Cell.SIZE;
            }
            y += Cell.SIZE;
        }
    }

    // Instance methods =======================================================

    public void draw(final Graphics graphics) {
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            for (int colNum=0; colNum < _nCols; colNum++) {
                graphics.setMatrix(_origins[rowNum][colNum]);
                _cells[rowNum][colNum].draw(graphics);
            }
        }
    }

    public Cell getCell(final int rowNum, final int colNum) {
        if (rowNum < 0 || rowNum >= _nRows || colNum < 0 || colNum >= _nCols) {
            return null;
        }
        return _cells[rowNum][colNum];
    }

    public void move(final Entity entity, final Direction direction) {
        Cell cell = entity.getCell();
        Cell nextCell = cell.getAdjacent(direction);
        if (nextCell != null) {
            if (nextCell.add(entity)) {
                cell.remove(entity);
            }
        }
    }

    public boolean addEntity(final int rowNum, final int colNum, final Entity entity) {
        Cell cell = getCell(rowNum, colNum);
        if (cell != null) {
            return cell.add(entity);
        }
        return false;
    }

}
