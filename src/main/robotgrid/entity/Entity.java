package robotgrid.entity;

import processing.core.PMatrix;
import processing.core.PMatrix2D;
import robotgrid.graphics.Graphics;
import robotgrid.graphics.Pen;
import robotgrid.scene.Cell;
import robotgrid.scene.Direction;
import robotgrid.utils.Logger;
import robotgrid.utils.Result;
import robotgrid.world.World;

public abstract class Entity {

    // Static inner classes ===================================================
    // Static variables =======================================================

        /**
     * Any device that does not override the deviceLatency() method will use this
     * value for its latency.
     */
    protected static float _STANDARD_LATENCY = 1000.0f;

    private static Logger _logger = new Logger(Entity.class, Logger.Level.Debug);

    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    public final String name;

    protected Cell _cell = null;
    protected Height _height = Height.Low;
    protected Direction _direction = Direction.North;

    // These are values for drawing the Entity in the graphics window.
    protected float _x = 0.0f;
    protected float _y = 0.0f;
    protected float _angle = 0.0f;
    protected PMatrix _rotationMatrix = new PMatrix2D();
    protected Pen _pen = new Pen(0xFF_FF_FF_FF, 0xFF_00_00_00, 1.0f);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public Entity(final String name) {
        this.name = name;
    }

   public Entity setDirection(final Direction direction) {
        _direction = direction;
        _angle = _direction.getAngle();
        _rotationMatrix = new PMatrix2D();
        _rotationMatrix.rotate(_angle);
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
     * This method is used by subclasses to simulate real-world latency of motion.
     * I guess that's called inertia.
     */
    public void delay() {
        long delay = (long)(deviceLatency() * World.SIMULATION_SPEED);
        try {
            Thread.sleep(delay);
        }
        catch (InterruptedException exn) {
            _logger.warn("delay(", delay, "): thread interrupted for object " + this);
        }
    }

    public float deviceLatency() {
        return _STANDARD_LATENCY;
    }

    /**
     * Subclasses should override, but also invoke this super-method.
     */
    public void draw(final Graphics graphics, final int layerNum) {
        graphics.setPen(_pen);
        if (_x != 0 || _y != 0) {
            graphics.translate(_x, _y);
        }
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

    public void setAngle(final float angle) {
        _angle = angle;
    }

    public void setXY(final float x, final float y) {
        _x = x;
        _y = y;
    }

    public Result<Void, String> rotateLeft() {
        delay();
        setDirection(_direction.turnLeft());
        return new Result.Success<>();
    }

    public Result<Void, String> rotateRight() {
        delay();
        setDirection(_direction.turnRight());
        return new Result.Success<>();
    }

    @Override
    public String toString() {
        return name;
    }

}
