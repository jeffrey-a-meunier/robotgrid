package robotgrid.entity2.table;

import processing.core.PGraphics;
import robotgrid.entity2.Entity2;
import robotgrid.entity2.View;
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

    public _View(final Entity2 entity) {
        super(entity);
    }

    // Instance methods =======================================================

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        PGraphics layer = graphics.layer(layerNum);
        float size = Cell.SIZE * 0.9f;
        float pos = -size / 2.0f;
        layer.rect(pos, pos, size, size);
        Entity2 payload = _entity.payload();
        if (payload != null) {
            payload.draw(graphics, layerNum + 1);
        }
    }

}
