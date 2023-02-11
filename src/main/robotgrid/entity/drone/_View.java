package robotgrid.entity.drone;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.entity.View;
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

    public _View(final Entity entity) {
        super(entity);
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
        layer.strokeWeight(5);
        layer.line(x1, y1, x2, y2);
        layer.line(x1, y2, x2, y1);
        // draw the body square over the X
        _body.draw(layer);
        // draw the rotors over the body
        layer.fill(128, 128, 128, 50);
        layer.circle(-x1, -y1, _bodySize2);
        layer.circle(x1, y1, _bodySize2);
        layer.circle(x1, -y1, _bodySize2);
        layer.circle(-x1, y1, _bodySize2);
        // draw the payload over the rotors
        // TODO make the body smaller and draw the payload under the body
        _drawPayload(graphics, layerNum);
    }

}
