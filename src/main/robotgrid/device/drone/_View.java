package robotgrid.device.drone;

import processing.core.PGraphics;
import robotgrid.device.device.Device;
import robotgrid.device.device.View;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.shape.Shape;
import robotgrid.shape.SquareShape;

class _View extends View {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance methods =======================================================

    protected float _bodySize = Cell.SIZE * 0.9f;
    protected float _bodySize2 = _bodySize / 2.0f;
    protected float _bodySize3 = _bodySize / 3.0f;
    protected float _bodySize4 = _bodySize / 4.0f;
    protected Shape _body = new SquareShape(Cell.SIZE / 4f);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public _View(final Device device) {
        super(device);
    }

    // Instance methods =======================================================

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        PGraphics layer = graphics.layer(layerNum);
        // draw an X
        float x1 = -_bodySize4 - 2;
        float y1 = -_bodySize4 - 2;
        float x2 = _bodySize4 + 2;
        float y2 = _bodySize4 + 2;
        layer.strokeWeight(5.0f);
        // draw the rotor arms
        layer.line(x1, y1, x2, y2);
        layer.line(x1, y2, x2, y1);
        _body.draw(layer);
        if (_device.isOn()) {
            _drawMovingRotors(layer, x1, y1, x2, y2);
        }
        else {
            _drawStaticRotors(layer, x1, y1, x2, y2);
        }
        // TODO make the body smaller and draw the payload under the body
        _drawPayload(graphics, layerNum);
    }

    protected void _drawStaticRotors(final PGraphics layer, final float x1, final float y1, final float x2, final float y2) {
        float bodySize8 = _bodySize / 8.0f;
        float x1a = x1 - bodySize8;
        float x1b = x1 + bodySize8;
        float y1a = y1 - bodySize8;
        float y1b = y1 + bodySize8;
        float x2a = x2 - bodySize8;
        float x2b = x2 + bodySize8;
        float y2a = y2 - bodySize8;
        float y2b = y2 + bodySize8;
        layer.strokeWeight(3.0f);
        layer.line(x1b, y1a, x1a, y1b);
        layer.line(x2a, y1a, x2b, y1b);
        layer.line(x1a, y2a, x1b, y2b);
        layer.line(x2a, y2b, x2b, y2a);
    }

    protected void _drawMovingRotors(final PGraphics layer, final float x1, final float y1, final float x2, final float y2) {
        layer.fill(128, 128, 128, 50);
        layer.circle(-x1, -y1, _bodySize2);
        layer.circle(x1, y1, _bodySize2);
        layer.circle(x1, -y1, _bodySize2);
        layer.circle(-x1, y1, _bodySize2);
    }

}
