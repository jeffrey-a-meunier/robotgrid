package robotgrid.scene;

public class AirGrid extends Grid {

    // Static inner classes ===================================================
    // Static variables =======================================================
    // Static initializer =====================================================
    // Static methods =========================================================
    // Instance inner classes =================================================
    // Instance variables =====================================================
    // Instance initializer ===================================================
    // Constructors ===========================================================

    public AirGrid(Scene scene, int nRows, int nCols, int cellWidth, int cellHeight) {
        super(scene, nRows, nCols, cellWidth, cellHeight);
        setLayerType(Grid.LayerType.Air);
    }

    // Instance methods =======================================================

    @Override
    public Cell createCell(final Grid grid, final int rowNum, final int colNum) {
        return new AirCell(grid, rowNum, colNum);
    }

}
