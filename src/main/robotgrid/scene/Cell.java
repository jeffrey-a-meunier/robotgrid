package robotgrid.scene;

import java.util.List;
import java.util.Optional;

import processing.core.PGraphics;
import robotgrid.device.IContainer;
import robotgrid.device.device.Device;
import robotgrid.device.widget.Widget;
import robotgrid.graphics.Graphics;
import robotgrid.graphics.Pen;
import robotgrid.server.Client;

public abstract class Cell implements IContainer {

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
    protected Device _device;
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

    public synchronized boolean addContent(final Device device) {
        if (_device == null) {
            _device = device;
            device.setContainer(this);
            Client.INFO.contentAddedNotice(this, _grid.layerType(), device);
            return true;
        }
        return _device.addContent(device);
    }

    public int contentCount() {
        if (_device == null) {
            return 0;
        }
        return _device.contentCount();
    }

    public Optional<Device> peekContent() {
        if (_device == null) {
            return Optional.empty();
        }
        if (_device instanceof Widget) {
            return Optional.of(_device);
        }
        return _device.peekContent();
    }

    public synchronized boolean removeDevice(final Device device) {
        if (_device == device) {
            _device = null;
            Client.INFO.contentRemovedNotice(this, _grid.layerType(), device);
            return true;
        }
        return false;
    }

    public synchronized Optional<Device> removeContent() {
        if (_device == null) {
            return Optional.empty();
        }
        if (_device instanceof Widget) {
            Device widget = _device;
            _device = null;
            Client.INFO.contentRemovedNotice(this, _grid.layerType(), widget);
            return Optional.of(widget);
        }
        return _device.removeContent();
    }

    public Optional<Device> removeContent(final Device payload) {
        return removeContent();
    }

    public String name() {
        return "Cell{" + _rowNum + ',' + _colNum + '}';
    }

    public void draw(final Graphics graphics) {
        _drawBackground(graphics.layer(0));
        if (_device != null) {
            _device.draw(graphics, 1);
        }
    }

    protected void _drawBackground(final PGraphics graphics) {
        _pen.applyTo(graphics);
        graphics.square(-SIZE2, -SIZE2, SIZE1);
    }

    public Device device() {
        return _device;
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
        String deviceString = _device == null ? "None" : _device.toString();
        strings.add(layerTypeString + "Device=" + deviceString);
    }

    @Override
    public Grid.LayerType layerType() {
        return _grid.layerType();
    }

    @Override
    public String toString() {
        return "Cell[row=" + _rowNum + ",col=" + _colNum + ']';
    }

}
