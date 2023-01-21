package robotgrid.entity.active.robot;

import processing.core.PApplet;
import robotgrid.entity.Entity;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.shape.CircleShape;
import robotgrid.shape.Shape;
import robotgrid.shape.TriangleShape;

public class MobileRobot extends Robot {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Entity _payload;
    protected Direction _direction = Direction.North;

    protected float _bodySize = Cell.SIZE * 0.9f;
    protected float _bodySize2 = _bodySize / 2f;
    protected Shape _body = new CircleShape(_bodySize);
    protected float _indicatorSize = Cell.SIZE / 3.0f;
    protected float _indicatorSize2 = _indicatorSize / 2f;
    protected Shape _indicator = new TriangleShape(_indicatorSize);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public MobileRobot(final String name) {
        super(name);
        _controller = new MobileRobotController().setEntity(this);
    }

    // Instance methods =======================================================

    @Override
    public boolean addPayload(final Entity payload) {
        if (_payload == null) {
            _payload = payload;
            return true;
        }
        return false;
    }

    @Override
    public void draw_aux(final PApplet applet) {
        _body.draw(applet);
        // move the tip of the indicator so that it's tangent to the edge of the body
        applet.pushMatrix();
        applet.translate(0, -_bodySize2 + _indicatorSize2);
        _indicator.draw(applet);
        // draw the payload
        applet.popMatrix();
        if (_payload != null) {
            _payload.draw(applet);
        }
    }

    @Override
    public Entity removePayload() {
        Entity payload = _payload;
        _payload = null;
        return payload;
    }

    // Controller methods =====================================================

    public CommandResult rotateLeft() {
        _direction = _direction.turnLeft();
        _angle = _direction.getAngle();
        return CommandResult.SUCCESS;
    }

    public CommandResult rotateRight() {
        _direction = _direction.turnRight();
        _angle = _direction.getAngle();
        return CommandResult.SUCCESS;
    }

    public CommandResult moveForward() {
        _cell.grid().move(this, _direction);
        return CommandResult.SUCCESS;
    }

    public CommandResult moveBackward() {
        _cell.grid().move(this, _direction.turnRight().turnRight());
        return CommandResult.SUCCESS;
    }

}
