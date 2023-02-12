package robotgrid.scene;

import java.util.List;
import java.util.Optional;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.entity.IContainer;
import robotgrid.entity.widget.Widget;
import robotgrid.graphics.Graphics;
import robotgrid.graphics.Pen;
import robotgrid.server.Client;

public class Cell implements IContainer {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static final int _FILL_COLOR = 0xFF_a0_a0_a0;
    protected static final int _LINE_COLOR = 0xFF_80_80_80;
    protected static final float _LINE_WIDTH = 1;

    public static float SIZE;
    public static float SIZE1 = SIZE - _LINE_WIDTH;
    public static float SIZE2 = SIZE / 2.0f;

    // Static initializer =====================================================
    // Static methods =========================================================

    public static void setSize(final float size) {
        SIZE = size;
        SIZE1 = SIZE - _LINE_WIDTH;
        SIZE2 = SIZE / 2.0f;
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected int _rowNum;
    protected int _colNum;
    protected Entity _entity;
    protected Grid _grid;
    protected Pen _pen = new Pen(_FILL_COLOR, _LINE_COLOR, _LINE_WIDTH);  // TODO separate view from model
    
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Cell(final Grid grid, final int rowNum, final int colNum) {
        _grid = grid;
        _rowNum = rowNum;
        _colNum = colNum;
    }

    // Instance methods =======================================================

    public synchronized boolean addPayload(final Entity payload) {
        if (_entity == null) {
            _entity = payload;
            payload.setContainer(this);
            Client.INFO.payloadNotice(this, payload);
            return true;
        }
        return _entity.addPayload(payload);
    }

    public int payloadCount() {
        if (_entity == null) {
            return 0;
        }
        return _entity.payloadCount();
    }

    public Optional<Entity> peekPayload() {
        if (_entity == null) {
            return Optional.empty();
        }
        if (_entity instanceof Widget) {
            return Optional.of(_entity);
        }
        return _entity.peekPayload();
    }

    public synchronized boolean removeEntity(final Entity entity) {
        if (_entity == entity) {
            _entity = null;
            return true;
        }
        return false;
    }

    public synchronized Optional<Entity> removePayload() {
        if (_entity == null) {
            return Optional.empty();
        }
        if (_entity instanceof Widget) {
            Entity widget = _entity;
            _entity = null;
            Client.INFO.payloadNotice(this, null);
            return Optional.of(widget);
        }
        return _entity.removePayload();
    }

    public Optional<Entity> removePayload(final Entity payload) {
        return removePayload();
    }

    public String name() {
        return "Cell{" + _rowNum + ',' + _colNum + '}';
    }

    public void draw(final Graphics graphics) {
        _drawBackground(graphics.layer(0));
        if (_entity != null) {
            _entity.draw(graphics, 1);
        }
    }

    protected void _drawBackground(final PGraphics graphics) {
        _pen.applyTo(graphics);
        graphics.square(-SIZE2, -SIZE2, SIZE1);
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

    public int row() {
        return _rowNum;
    }

    public int col() {
        return _colNum;
    }

    public void info(final List<String> strings) {
        String layerTypeString = "" + _grid.layerType();
        String entityString = _entity == null ? "None" : _entity.toString();
        strings.add(layerTypeString + "Entity=" + entityString);
    }

    @Override
    public String toString() {
        return "Cell[row=" + _rowNum + ",col=" + _colNum + ']';
    }

}
