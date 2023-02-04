package robotgrid.scene;

public enum Direction {

    North("North"),
    East("East"),
    South("South"),
    West("West");

    // Static inner classes ===================================================
    // Static variables =======================================================

    protected final int nEnums = 4;
    protected final float turnIncrement = (float)(Math.PI * 2.0 / (double)nEnums);

    // Static initializer =====================================================
    // Static methods =========================================================

    public static Direction parse(final String directionString, final Direction deflt) {
        String directionString1 = directionString.toLowerCase();
        switch (directionString1) {
            case "north": return North;
            case "east": return East;
            case "south": return South;
            case "west": return West;
        }
        return deflt;
    }

    // Instance inner classes =================================================
    // Instance variables =====================================================

    protected String _name;

    // Instance initializer ===================================================
    // Constructors ===========================================================

    Direction(final String name) {
        _name = name;
    }

    // Instance methods =======================================================

    public float getAngle() {
        return (float)ordinal() * turnIncrement;
    }

    /**
     * Java's % operator is *remainder*, not *modulus*.
     */
    protected int modulus(final int dividend, final int divisor) {
        int remainder = dividend % divisor;
        return remainder < 0 ? (remainder + divisor) : remainder;
    }
 
    public Direction turnLeft() {
        int index = modulus(ordinal() - 1, 4);
        return Direction.values()[index];
    }

    public Direction turnRight() {
        int index = modulus(ordinal() + 1, 4);
        return Direction.values()[index];
    }

    public Direction reverse() {
        int index = modulus(ordinal() + 2, 4);
        return Direction.values()[index];
    }

}
