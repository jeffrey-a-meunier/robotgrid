package robotgrid.scene;

import processing.core.PApplet;
import robotgrid.entity.Entity;

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

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Grid(final int nRows, final int nCols, int cellWidth, int cellHeight) {
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

    protected void draw(final PApplet applet, final int x, final int y) {
        applet.translate(Cell.WIDTH2, Cell.HEIGHT2);
        applet.pushMatrix();
        // draw the background of each cell
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            applet.pushMatrix();
            for (int colNum=0; colNum < _nCols; colNum++) {
                _cells[rowNum][colNum].drawBackground(applet);
                applet.translate(Cell.WIDTH, 0f);
            }
            applet.popMatrix();
            applet.translate(0f, Cell.HEIGHT);
        }
        applet.popMatrix();
        // draw the content of each cell
        for (int rowNum=0; rowNum < _nRows; rowNum++) {
            applet.pushMatrix();
            for (int colNum=0; colNum < _nCols; colNum++) {
                _cells[rowNum][colNum].drawContent(applet);
                applet.translate(Cell.WIDTH, 0f);
            }
            applet.popMatrix();
            applet.translate(0f, Cell.HEIGHT);
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
                cell.removeEntity(entity);
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
