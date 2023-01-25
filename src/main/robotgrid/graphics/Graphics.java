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
        return layer;
    }

    public void drawAllLayers(final float x, final float y) {
        for (PGraphics layer : _layers) {
            layer.endDraw();
            _applet.image(layer, x, y);
            layer.clear();
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

}
