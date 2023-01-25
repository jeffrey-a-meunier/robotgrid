package robotgrid.entity;

import robotgrid.entity.active.controller.CommandResult;
import robotgrid.graphics.Graphics;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;

public abstract class Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;
    protected Cell _cell = null;
    protected Height _height = Height.Low;
    protected Direction _direction = Direction.North;

    // These are values for drawing the Entity in the graphics window.
    protected float _x = 0.0f;
    protected float _y = 0.0f;
    protected float _angle = 0.0f;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Entity(final String name) {
        _name = name;
    }

    public Entity setDirection(final Direction direction) {
        _direction = direction;
        _angle = _direction.getAngle();
        return this;
    }

    // Instance methods =======================================================

    public boolean addPayload(final Entity payload) {
        return false;
    }

    public Entity removePayload() {
        return null;
    }

    public Entity payload() {
        return null;
    }

    public Direction direction() {
        return _direction;
    }

    /**
     * Subclasses should override, but also invoke this super-method.
     */
    public void draw(final Graphics graphics, final int layerNum) {
        graphics.translate(_x, _y);
        graphics.rotate(_angle);
    }

    public Cell getCell() {
        return _cell;
    }

    public Height height() {
        return _height;
    }

    public Entity setCell(final Cell cell) {
        _cell = cell;
        return this;
    }

    public void setXY(final float x, final float y) {
        _x = x;
        _y = y;
    }

    public CommandResult rotateLeft() {
        setDirection(_direction.turnLeft());
        return CommandResult.SUCCESS;
    }

    public CommandResult rotateRight() {
        setDirection(_direction.turnRight());
        return CommandResult.SUCCESS;
    }

    @Override
    public String toString() {
        return _name;
    }

}
