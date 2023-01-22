package robotgrid.entity.active.conveyor;

import processing.core.PApplet;
import robotgrid.entity.Entity;
import robotgrid.entity.active.ActiveEntity;
import robotgrid.scene.Cell;
import robotgrid.scene.Color;

public class Conveyor extends ActiveEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Entity _payload = null;

    protected Color _fillColor = new Color(0xFF_FF_FF_FF);
    protected Color _lineColor = new Color(0xFF_00_00_00);
    protected Color _lineColor2 = new Color(0xFF_80_80_80);
    protected float _lineSize = 1.0f;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Conveyor(final String name) {
        super(name);
        _controller = new ConveyorController().setEntity(this);
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
    public void draw_aux(final PApplet applet) {
        float pos = -Cell.SIZE / 2.0f;
        _fillColor.applyFill(applet);
        _lineColor.applyStroke(applet);
        applet.strokeWeight(_lineSize);
        applet.rect(pos, pos, Cell.SIZE, Cell.SIZE);
        applet.fill(0x00_FF_FF_FF);
        _drawLines(applet);
        _drawPayload(applet);
    }

    protected void _drawLines(final PApplet applet) {
        _lineColor2.applyStroke(applet);
        float x0 = Cell.SIZE / 2.0f;
        float x1 = -x0;
        float dy6 = Cell.SIZE / 6.0f;
        float y0 = -Cell.SIZE / 2.0f;
        float y1 = y0 + dy6;
        for (int n=0; n<6; n++) {
            applet.line(x0, y1, 0, y0);
            applet.line(x1, y1, 0, y0);
            y0 = y1;
            y1 += dy6;
        }
    }

    protected void _drawPayload(final PApplet applet) {
        if (_payload != null) {
            _payload.draw(applet);
        }
    }

}
