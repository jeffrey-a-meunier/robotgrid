package robotgrid.entity.active.robot;

import processing.core.PGraphics;
import robotgrid.entity.Entity;
import robotgrid.entity.Height;
import robotgrid.entity.active.ActiveEntity;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.entity.widget.Widget;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;

public class ArticulatedRobot extends ActiveEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Entity _payload;
    protected float _speed = (float)(Math.PI / 4.0);  // radians per second

    protected boolean _isExtended = false;
    protected boolean _isGripping = false;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ArticulatedRobot(final String name) {
        super(name);
        _height = Height.High;
        _controller = new ArticulatedRobotController().setEntity(this);
    }

    // Instance methods =======================================================

    public void addPayload(final Widget widget) {
        _payload = widget;
    }

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        _drawBase(graphics, layerNum);
        _drawArm(graphics, layerNum + 2);

    }

    protected void _drawBase(final Graphics graphics, final int layerNum) {
        PGraphics layer = graphics.layer(layerNum);
        float size = Cell.SIZE * 0.9f;
        float pos = -size / 2.0f;
        layer.rect(pos, pos, size, size);
        layer.circle(0.0f, 0.0f, Cell.SIZE * 0.7f);
    }

    protected float _eoatPos;
    protected float _payloadY = 0.0f;

    protected void _drawArm(final Graphics graphics, final int layerNum) {
        PGraphics layer = graphics.layer(layerNum);
        float armWidth = Cell.SIZE / 3.0f;
        float armWidth2 = armWidth / 2.0f;
        float armLength;
        if (_isExtended) {
            armLength = Cell.SIZE - armWidth2;
        }
        else {
            armLength = armWidth;
        }
        layer.rect(-armWidth2, armWidth2, armWidth, -armLength);
        _eoatPos = -armLength + armWidth2;
        _payloadY = _eoatPos - armWidth;
        _drawGripper(graphics, layerNum);
    }


    protected void _drawGripper(final Graphics graphics, final int layerNum) {
        PGraphics layer = graphics.layer(layerNum);
        float armWidth = Cell.SIZE / 3.0f;
        float armWidth2 = armWidth / 2.0f;
        float x1, y1, x2, y2, x3, y3;
        if (_isGripping) {
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
        _drawPayload(graphics, layerNum - 1);
    }

    protected void _drawPayload(final Graphics graphics, final int layerNum) {
        PGraphics layer = graphics.layer(layerNum);
        if (_payload != null) {
            layer.translate(0.0f, _payloadY);
            _payload.draw(graphics, layerNum);
        }
    }

    // Controller methods =====================================================

    public CommandResult armExtend() {
        Cell adjacentCell = _cell.getAdjacent(_direction);
        if (adjacentCell == null) {
            return new CommandResult.Failure("Arm is blocked");
        }
        Entity entity = adjacentCell.entity();
        if (entity != null) {
            if (entity.height() == Height.High) {
                return new CommandResult.Failure("Entity in adjacent cell is preventing arm extend");
            }
        }
        _isExtended = true;
        return CommandResult.SUCCESS;
    }

    public CommandResult armRetract() {
        _isExtended = false;
        return CommandResult.SUCCESS;
    }

    public CommandResult gripperGrip() {
        if (_isExtended && !_isGripping) {
            if(_payload == null) {
                Cell adjacentCell = _cell.getAdjacent(_direction);
                _payload = adjacentCell.removePayload();
            }
        }
        _isGripping = true;
        return CommandResult.SUCCESS;
    }

    public CommandResult gripperRelease() {
        if (_isExtended && _payload != null) {
            Cell adjacentCell = _cell.getAdjacent(_direction);
            if (adjacentCell.add(_payload)) {
                _payload = null;
                _isGripping = false;
            }
        }
        return CommandResult.SUCCESS;
    }

}
