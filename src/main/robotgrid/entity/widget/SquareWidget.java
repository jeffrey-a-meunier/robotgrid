package robotgrid.entity.widget;

import robotgrid.scene.Cell;
import robotgrid.shape.SquareShape;

public class SquareWidget extends Widget {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public SquareWidget(final String name) {
        super(name, new SquareShape(Cell.SIZE / 3f));
    }

    // Instance methods =======================================================

    @Override
    public String typeName() {
        return "SquareWidget";
    }

}
