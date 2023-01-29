package robotgrid.entity.active.conveyor;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.entity.active.ActiveEntity;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;

public class Conveyor extends ActiveEntity {

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

    public Conveyor(final String name) {
        super(name, new ConveyorController(name + ".Controller"));
    }

    public Conveyor(final String name, final ConveyorController controller) {
        super(name, controller);
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
        float pos = -Cell.SIZE / 2.0f;
        layer.rect(pos, pos, Cell.SIZE, Cell.SIZE);
        _drawLines(layer);
        if (_payload != null) {
            _payload.draw(graphics, layerNum + 1);
        }
    }

    protected void _drawLines(final PGraphics layer) {
        float x0 = Cell.SIZE / 2.0f;
        float x1 = -x0;
        float dy6 = Cell.SIZE / 6.0f;
        float y0 = -Cell.SIZE / 2.0f;
        float y1 = y0 + dy6;
        for (int n=0; n<6; n++) {
            layer.line(x0, y1, 0, y0);
            layer.line(x1, y1, 0, y0);
            y0 = y1;
            y1 += dy6;
        }
    }

}
