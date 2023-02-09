package robotgrid.entity.movingBase;

import robotgrid.entity.Entity;
import robotgrid.entity.View;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.shape.CircleShape;
import robotgrid.shape.Shape;
import robotgrid.shape.TriangleShape;

class _View extends View {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected float _bodySize = Cell.SIZE * 0.9f;
    protected float _bodySize2 = _bodySize / 2f;
    protected Shape _body = new CircleShape(_bodySize);
    protected float _indicatorSize = _bodySize / 4.0f;
    protected Shape _indicator = new TriangleShape(0, -_bodySize2, _indicatorSize);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public _View(final Entity entity) {
        super(entity);
    }

    // Instance methods =======================================================

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        _body.draw(graphics.layer(layerNum));
        _indicator.draw(graphics.layer(layerNum));
        Entity payload = _entity.payload();
        if (payload != null) {
            payload.draw(graphics, layerNum + 1);
        }
    }

}
