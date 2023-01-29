package robotgrid.entity.active.robot;

import robotgrid.entity.Entity;
import robotgrid.entity.active.ActiveEntity;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.shape.CircleShape;
import robotgrid.shape.Shape;
import robotgrid.shape.TriangleShape;

public class MobileRobot extends ActiveEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Entity _payload;

    protected float _bodySize = Cell.SIZE * 0.9f;
    protected float _bodySize2 = _bodySize / 2f;
    protected Shape _body = new CircleShape(_bodySize);
    protected float _indicatorSize = _bodySize / 4.0f;
    protected Shape _indicator = new TriangleShape(0, -_bodySize2, _indicatorSize);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public MobileRobot(final String name) {
        super(name, new MobileRobotController(name));
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
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        _body.draw(graphics.layer(layerNum));
        _indicator.draw(graphics.layer(layerNum));
        if (_payload != null) {
            _payload.draw(graphics, layerNum + 1);
        }
    }

    @Override
    public Entity removePayload() {
        Entity payload = _payload;
        _payload = null;
        return payload;
    }

    // Controller methods =====================================================

    public CommandResult moveForward() {
        _cell.grid().move(this, _direction);
        return CommandResult.SUCCESS;
    }

    public CommandResult moveBackward() {
        _cell.grid().move(this, _direction.turnRight().turnRight());
        return CommandResult.SUCCESS;
    }

}
