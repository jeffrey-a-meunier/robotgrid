package robotgrid.device.device;

import java.util.Optional;

import processing.core.PMatrix;
import processing.core.PMatrix2D;
import robotgrid.graphics.Graphics;
import robotgrid.graphics.Pen;

public abstract class View {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected Device _device;

    protected float _x = 0.0f;
    protected float _y = 0.0f;
    protected float _angle = 0.0f;
    protected PMatrix _rotationMatrix = new PMatrix2D();
    protected Pen _pen = new Pen(0xFF_FF_FF_FF, 0xFF_00_00_00, 1.0f);

    // Instance initializer ===================================================
    // Constructors ===========================================================

    public View(final Device device) {
        _device = device;
    }

    // Instance methods =======================================================

    /**
     * Subclasses should override AND ALSO invoke this super-method.
     */
    public void draw(final Graphics graphics, final int layerNum) {
        graphics.setPen(_pen);
        if (_x != 0 || _y != 0) {
            graphics.translate(_x, _y);
        }
        graphics.rotate(_angle);
    }

    /**
     * This draws a single payload object, if it exists. If a device is to
     * contain more than one payload object then you need to override this
     * method to draw them. See the Table class for an example.
     */
    protected void _drawPayload(final Graphics graphics, final int layerNum) {
        Optional<Device> payload_opt = _device.peekContent();
        if (payload_opt.isPresent()) {
            Device payload = payload_opt.get();
            payload.draw(graphics, layerNum);
        }
    }

    public void setAngle(final float angle) {
        _angle = angle;
    }

}
