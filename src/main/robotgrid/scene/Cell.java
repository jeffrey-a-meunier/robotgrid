package robotgrid.scene;

import processing.core.PApplet;
import robotgrid.entity.Entity;
import robotgrid.entity.IContainer;
import robotgrid.entity.active.robot.ArticulatedRobot;
import robotgrid.entity.widget.Widget;

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
    public boolean remove(final Entity entity) {
        if (entity == _entity) {
            remove();
            return true;
        }
        return false;
    }

    @Deprecated
    public boolean removeEntity(final Entity entity) {
        if (_entity == entity) {
            _entity = null;
            return true;
        }
        return false;
    }

    public boolean addPayload(final Entity payload) {
        if (_entity == null) {
            _entity = payload;
            return true;
        }
        return _entity.addPayload(payload);
    }

    public Entity removePayload() {
        if (_entity == null) {
            return null;
        }
        if (_entity instanceof Widget) {
            Entity entity = _entity;
            _entity = null;
            return entity;
        }
        if (!(_entity instanceof ArticulatedRobot)) {
            return _entity.removePayload();
        }
        return null;
    }

    public void drawBackground(final PApplet applet) {
        _lineColor.applyStroke(applet);
        _fillColor.applyFill(applet);
        applet.strokeWeight(LINE_WIDTH);
        applet.rect(-WIDTH2, -HEIGHT2, WIDTH1, HEIGHT1);
    }
    
    public void drawContent(final PApplet applet) {
        if (_entity != null) {
            _lineColor.applyStroke(applet);
            _fillColor.applyFill(applet);
            applet.strokeWeight(LINE_WIDTH);
            _entity.draw(applet);
        }
    }

    public Entity entity() {
        return _entity;
    }

    public Cell getAdjacent(final Direction direction) {
        int rowNum = _rowNum;
        int colNum = _colNum;
        switch (direction) {
            case North:
                rowNum--;
                break;
            case East:
                colNum++;
                break;
            case South:
                rowNum++;
                break;
            case West:
                colNum--;
                break;
        }
        if (rowNum >= 0 && rowNum < _grid._nRows && colNum >= 0 && colNum < _grid._nCols) {
            return _grid.getCell(rowNum, colNum);
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
