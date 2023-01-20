package robotgrid.scene;

import processing.core.PApplet;
import robotgrid.entity.Entity;
import robotgrid.entity.IContainer;

public class Cell implements IContainer {

    // Static inner classes ===================================================
    // Static variables =======================================================

    public static final float SIZE = 80;

    protected static Color _fillColor = new Color(0xFF_a0_a0_a0);
    protected static Color _lineColor = new Color(0xFF_80_80_80);

    protected static final float LINE_WIDTH = 1;

    protected static final float HEIGHT = SIZE;
    protected static final float HEIGHT1 = HEIGHT - LINE_WIDTH;
    protected static final float HEIGHT2 = HEIGHT / 2;
    protected static final float WIDTH = SIZE;
    protected static final float WIDTH1 = WIDTH - LINE_WIDTH;
    protected static final float WIDTH2 = WIDTH / 2;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _rowNum;
    protected int _colNum;
    protected Entity _entity;
    protected Grid _grid;
    
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Cell(final Grid grid, final int rowNum, final int colNum) {
        _grid = grid;
        _rowNum = rowNum;
        _colNum = colNum;
    }

    // Instance methods =======================================================

    @Override
    public boolean add(Entity entity) {
        if (_entity == null) {
            _entity = entity;
            entity.setCell(this);
            return true;
        }
        return false;
    }

    @Override
    public Entity remove() {
        Entity entity = _entity;
        _entity = null;
        return entity;
    }

    @Override
    public boolean remove(Entity entity) {
        if (entity == _entity) {
            remove();
            return true;
        }
        return false;
    }

    public boolean removeEntity(final Entity entity) {
        if (_entity == entity) {
            _entity = null;
            return true;
        }
        return false;
    }

    public void draw(final PApplet applet) {
        _lineColor.applyStroke(applet);
        _fillColor.applyFill(applet);
        applet.strokeWeight(LINE_WIDTH);
        applet.rect(-WIDTH2, -HEIGHT2, WIDTH1, HEIGHT1);
        if (_entity != null) {
            _entity.draw(applet);
        }
    }

    public Cell getAdjacent(final Direction direction) {
        switch (direction) {
            case North:
                return _grid.getCell(_rowNum - 1, _colNum);
            case East:
                return _grid.getCell(_rowNum, _colNum + 1);
            case South:
                return _grid.getCell(_rowNum + 1, _colNum);
            case West:
                return _grid.getCell(_rowNum, _colNum - 1);
        }
        return null;
    }

    public Grid grid() {
        return _grid;
    }

    @Override
    public String toString() {
        return "Cell[row=" + _rowNum + ",col=" + _colNum + ']';
    }

}
