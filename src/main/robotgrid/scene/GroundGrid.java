package robotgrid.scene;

public class GroundGrid extends Grid {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public GroundGrid(Scene scene, int nRows, int nCols, int cellWidth, int cellHeight) {
        super(scene, nRows, nCols, cellWidth, cellHeight);
        setLayerType(Grid.LayerType.Ground);
    }

    // Instance methods =======================================================

    @Override
    public Cell createCell(final Grid grid, final int rowNum, final int colNum) {
        return new GroundCell(grid, rowNum, colNum);
    }

}
