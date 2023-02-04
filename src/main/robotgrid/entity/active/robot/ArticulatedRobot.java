package robotgrid.entity.active.robot;

import robotgrid.entity.Entity;
import robotgrid.entity.Height;
import robotgrid.entity.active.ActiveEntity;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.utils.Result;

public class ArticulatedRobot extends ActiveEntity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected RotatingBase _base;
    protected Arm _arm;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public ArticulatedRobot(final String name) {
        super(name, new ArticulatedRobotController(name));
        _arm = new Arm(name + ".Arm");
        _base = new RotatingBase(name + ".Base");
        _base.attachEntity(_arm);
        _height = Height.High;
    }

    // Instance methods =======================================================

    @Override
    public boolean addPayload(final Entity payload) {
        return _arm.addPayload(payload);
    }

    @Override
    public void draw(final Graphics graphics, final int layerNum) {
        super.draw(graphics, layerNum);
        _base.draw(graphics, layerNum);  // draws the arm automatically
    }

    @Override
    public void powerOn() {
        super.powerOn();
        _base.powerOn();
        _arm.powerOn();
    }

    @Override
    public void powerOff() {
        super.powerOff();
        _base.powerOff();
        _arm.powerOff();
    }

    @Override
    public Entity setCell(final Cell cell) {
        super.setCell(cell);
        _base.setCell(cell);
        _arm.setCell(cell);
        return this;
    }

    // Controller methods =====================================================

    public Result<Void, String> rotateLeft() {
        return _base.rotateLeft();
    }

    public Result<Void, String> rotateRight() {
        return _base.rotateRight();
    }

    public Result<Void, String> armExtend() {
        return _arm.extend();
    }

    public Result<Void, String> armRetract() {
        return _arm.retract();
    }

    public Result<Void, String> gripperGrip() {
        return _arm.grip();
    }

    public Result<Void, String> gripperRelease() {
        return _arm.release();
    }

}
