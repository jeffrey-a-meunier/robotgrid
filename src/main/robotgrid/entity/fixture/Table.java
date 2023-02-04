package robotgrid.entity.fixture;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;

public class Table extends Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Entity _payload = null;

    protected int _lineColor2 = 0xFF_80_80_80;
    protected float _lineSize = 1.0f;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Table(final String name) {
        super(name);
    }

    // Instance methods =======================================================

    @Override
    public synchronized boolean addPayload(final Entity payload) {
        if (_payload == null) {
            _payload = payload;
            return true;
        }
        return false;
    }

    @Override
    public Entity payload() {
        return _payload;
    }

    @Override
    public Entity removePayload() {
        Entity payload = _payload;
        _payload = null;
        return payload;
    }

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        PGraphics layer = graphics.layer(layerNum);
        float size = Cell.SIZE * 0.9f;
        float pos = -size / 2.0f;
        layer.rect(pos, pos, size, size);
        if (_payload != null) {
            _payload.draw(graphics, layerNum + 1);
        }
    }

}
