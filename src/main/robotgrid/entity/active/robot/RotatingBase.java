package robotgrid.entity.active.robot;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.entity.Height;
import robotgrid.entity.active.ActiveEntity;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;

public class RotatingBase extends ActiveEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected static float _STANDARD_DELAY = 1000.0f;

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    // A RotatingBase can have either a payload or an attached entity.
    // Each is treated differently.
    protected Entity _payload;
    protected Entity _attachedEntity;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public RotatingBase(final String name) {
        super(name, new RotatingBaseController(name));
        _height = Height.Low;
    }

    // Instance methods =======================================================

    public boolean addPayload(final Entity payload) {
        if (_attachedEntity == null & _payload == null) {
            _payload = payload;
            return true;
        }
        return false;
    }

    public boolean attachEntity(final Entity entity) {
        if (_attachedEntity == null & _payload == null) {
            _attachedEntity = entity;
            return true;
        }
        return false;
    }

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        _drawBase(graphics, layerNum);
        if (_attachedEntity != null) {
            _attachedEntity.draw(graphics, layerNum + 1);
        }
        else if (_payload != null) {
            _payload.draw(graphics, layerNum + 1);
        }
    }

    protected void _drawBase(final Graphics graphics, final int layerNum) {
        PGraphics layer = graphics.layer(layerNum);
        float size = Cell.SIZE * 0.9f;
        float pos = -size / 2.0f;
        layer.rect(pos, pos, size, size);
        layer.circle(0.0f, 0.0f, Cell.SIZE * 0.7f);
    }

    // Controller methods =====================================================

    @Override
    public CommandResult rotateLeft() {
        delay();
        if (_attachedEntity != null) {
            _attachedEntity.setDirection(_attachedEntity.direction().turnLeft());
        }
        else if (_payload != null) {
            _payload.setDirection(_payload.direction().turnLeft());
        }
        return CommandResult.SUCCESS;
    }

    @Override
    public CommandResult rotateRight() {
        delay();
        if (_attachedEntity != null) {
            _attachedEntity.setDirection(_attachedEntity.direction().turnRight());
        }
        else if (_payload != null) {
            _payload.setDirection(_payload.direction().turnRight());
        }
        return CommandResult.SUCCESS;
    }

}
