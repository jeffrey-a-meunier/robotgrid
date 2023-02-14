package robotgrid.graphics;

import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PMatrix;
import processing.core.PMatrix2D;

public class Graphics {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected PApplet _applet;
    protected List<PGraphics> _layers = new ArrayList<>();
    protected PMatrix _matrix = new PMatrix2D();
    protected Pen _pen = new Pen(0xFF_FF_FF_FF, 0xFF_00_00_00, 1.0f);
    protected int _highestLayer = 0;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Graphics(final PApplet applet) {
        _applet = applet;
    }

    // Instance methods =======================================================

    public PGraphics layer(final int layerNum) {
        PGraphics layer;
        while (layerNum >= _layers.size()) {
            layer = _applet.createGraphics(_applet.width, _applet.height);
            _layers.add(layer);
            layer.beginDraw();
        }
        layer = _layers.get(layerNum);
        layer.setMatrix(_matrix);
        _pen.applyTo(layer);
        if (layerNum > _highestLayer) {
            _highestLayer = layerNum;
        }
        return layer;
    }

    public void drawAllLayers() {
        for (PGraphics layer : _layers) {
            layer.endDraw();
            _applet.image(layer, 0, 0);
            layer.clear();
            _highestLayer = 0;
            layer.beginDraw();
        }
    }

    public Graphics resetMatrix() {
        _matrix.reset();
        return this;
    }

    public Graphics rotate(final float _angle) {
        _matrix.rotate(_angle);
        return this;
    }

    public Graphics translate(final float x, final float y) {
        _matrix.translate(x, y);
        return this;
    }

    public PMatrix getMatrix() {
        return _matrix;
    }

    public Graphics setMatrix(final PMatrix matrix) {
        _matrix = matrix;
        return this;
    }

    public Graphics setPen(final Pen pen) {
        _pen = pen;
        return this;
    }

}
