package robotgrid.entity.active.robot;

import processing.core.PApplet;
import robotgrid.entity.Entity;
import robotgrid.entity.active.controller.CommandResult;
import robotgrid.entity.widget.Widget;
import robotgrid.scene.Cell;
import robotgrid.scene.Color;
import robotgrid.scene.Direction;
import robotgrid.shape.CircleShape;
import robotgrid.shape.Shape;
import robotgrid.shape.TriangleShape;

public class ArticulatedRobot extends Robot {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Entity _payload;

    protected Direction _direction = Direction.North;
    protected float _speed = (float)(Math.PI / 4.0);  // radians per second

    protected float _bodySize = Cell.SIZE * 0.9f;
    protected float _bodySize2 = _bodySize / 2f;
    protected Shape _body = new CircleShape(_bodySize);
    protected float _indicatorSize = 20f;
    protected float _indicatorSize2 = _indicatorSize / 2f;
    protected Shape _indicator = new TriangleShape(_indicatorSize);

    protected boolean _isExtended = false;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ArticulatedRobot(final String name) {
        super(name);
        _controller = new ArticulatedRobotController().setEntity(this);
    }

    // Instance methods =======================================================

    public void addPayload(final Widget widget) {
        _payload = widget;
    }

    @Override
    public void draw_aux(final PApplet applet) {
        _drawBase(applet);
        _drawArm(applet);
        _drawGripper(applet);
        if (_payload != null) {
            _payload.draw(applet);
        }
    }

    protected Color _fillColor = new Color(0xFF_FF_FF_FF);
    protected Color _lineColor = new Color(0xFF_00_00_00);
    protected float _lineSize = 1.0f;

    protected void _drawBase(final PApplet applet) {
        float size = Cell.SIZE * 0.9f;
        float pos = -size / 2.0f;
        _fillColor.applyFill(applet);
        _lineColor.applyStroke(applet);
        applet.strokeWeight(_lineSize);
        applet.rect(pos, pos, size, size);
        applet.fill(0x00_FF_FF_FF);
        applet.stroke(0xFF_bf_bf_bf);
        applet.strokeWeight(3.0f);
        applet.circle(0.0f, 0.0f, Cell.SIZE * 0.7f);
    }

    protected float _eoatPos;

    protected void _drawArm(final PApplet applet) {
        float armWidth = Cell.SIZE / 3.0f;
        float armWidth2 = armWidth / 2.0f;
        _fillColor.applyFill(applet);
        _lineColor.applyStroke(applet);
        applet.strokeWeight(_lineSize);
        float armLength;
        if (_isExtended) {
            armLength = Cell.SIZE - armWidth2;
        }
        else {
            armLength = armWidth;
        }
        applet.rect(-armWidth2, armWidth2, armWidth, -armLength);
        _eoatPos = -armLength + armWidth2;
    }

    protected void _drawGripper(final PApplet applet) {
        float armWidth = Cell.SIZE / 3.0f;
        float armWidth2 = armWidth / 2.0f;
        float x1 = -armWidth2;
        float y1 = _eoatPos;
        float x2 = -armWidth;
        float y2 = _eoatPos - armWidth2;
        float x3 = x1;
        float y3 = _eoatPos - armWidth;
        _lineColor.applyStroke(applet);
        applet.strokeWeight(2.0f);
        applet.line(x1, y1, x2, y2);
        applet.line(x2, y2, x3, y3);
        applet.line(-x1, y1, -x2, y2);
        applet.line(-x2, y2, -x3, y3);
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

    public CommandResult armExtend() {
        _isExtended = true;
        return CommandResult.SUCCESS;
    }

    public CommandResult armRetract() {
        _isExtended = false;
        return CommandResult.SUCCESS;
    }

    public CommandResult gripperGrip() {
        // TODO complete this method
        System.out.println("ArticulatedRobot.gripperGrip() is not implemented");
        return CommandResult.SUCCESS;
    }

    public CommandResult gripperRelease() {
        // TODO complete this method
        System.out.println("ArticulatedRobot.gripperGrip() is not implemented");
        if (_payload != null) {
        }
        return CommandResult.SUCCESS;
    }

}
