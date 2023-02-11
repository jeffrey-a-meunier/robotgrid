package robotgrid.entity;

import java.util.Optional;

import processing.core.PMatrix;
import processing.core.PMatrix2D;
import robotgrid.graphics.Graphics;
import robotgrid.graphics.Pen;

public abstract class View {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Entity _entity;

    protected float _x = 0.0f;
    protected float _y = 0.0f;
    protected float _angle = 0.0f;
    protected PMatrix _rotationMatrix = new PMatrix2D();
    protected Pen _pen = new Pen(0xFF_FF_FF_FF, 0xFF_00_00_00, 1.0f);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public View(final Entity entity) {
        _entity = entity;
    }

    // Instance methods =======================================================

    /**
     * Subclasses should override AND ALSO invoke this super-method.
     */
    public void draw(final Graphics graphics, final int layerNum) {
        graphics.setPen(_pen);
        if (_x != 0 || _y != 0) {
            graphics.translate(_x, _y);
        }
        graphics.rotate(_angle);
    }

    protected void _drawPayload(final Graphics graphics, final int layerNum) {
        Optional<Entity> payload_opt = _entity.peekPayload();
        if (payload_opt.isPresent()) {
            Entity payload = payload_opt.get();
            payload.draw(graphics, layerNum);
        }
    }

    public void setAngle(final float angle) {
        _angle = angle;
    }

}
