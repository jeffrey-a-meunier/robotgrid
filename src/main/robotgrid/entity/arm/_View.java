package robotgrid.entity.arm;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.entity.View;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;

class _View extends View {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public _View(Entity entity) {
        super(entity);
    }

    // Instance methods =======================================================

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        _drawArm(graphics, layerNum + 2);
    }

    protected float _eoatPos;
    protected float _payloadY = 0.0f;

    protected void _drawArm(final Graphics graphics, final int layerNum) {
        PGraphics layer = graphics.layer(layerNum);
        float armWidth = Cell.SIZE / 3.0f;
        float armWidth2 = armWidth / 2.0f;
        float armLength;
        Arm arm = (Arm)_entity;
        if (arm.isExtended()) {
            armLength = Cell.SIZE - armWidth2;
        }
        else {
            armLength = armWidth;
        }
        layer.rect(-armWidth2, armWidth2, armWidth, -armLength);
        _eoatPos = -armLength + armWidth2;
        _payloadY = _eoatPos - armWidth;
        _drawGripper(arm, graphics, layerNum);
    }

    protected void _drawGripper(final Arm arm, final Graphics graphics, final int layerNum) {
        PGraphics layer = graphics.layer(layerNum);
        float armWidth = Cell.SIZE / 3.0f;
        float armWidth2 = armWidth / 2.0f;
        float x1, y1, x2, y2, x3, y3;
        if (arm.isGripping()) {
            x1 = -armWidth2;
            y1 = _eoatPos;
            x2 = -armWidth;
            y2 = _eoatPos - armWidth2;
            x3 = x1;
            y3 = _eoatPos - armWidth;
        }
        else {
            x1 = -armWidth2;
            y1 = _eoatPos;
            x2 = x1 - armWidth2;
            y2 = y1;
            x3 = x2;
            y3 = y2 - armWidth2;
        }
        layer.strokeWeight(2.0f);
        layer.line(x1, y1, x2, y2);
        layer.line(x2, y2, x3, y3);
        layer.line(-x1, y1, -x2, y2);
        layer.line(-x2, y2, -x3, y3);
        layer.strokeWeight(1.0f);
        graphics.translate(0, y3);
        _drawPayload(arm, graphics, layerNum - 1);
    }

    protected void _drawPayload(final Arm arm, final Graphics graphics, final int layerNum) {
        Entity payload = arm.payload();
        if (payload != null) {
            PGraphics layer = graphics.layer(layerNum);
            layer.translate(0.0f, _payloadY);
            payload.draw(graphics, layerNum);
        }
    }

}
