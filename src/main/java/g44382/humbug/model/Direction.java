package g44382.humbug.model;

/**
 *
 * @author El Fahsi Abdessalam
 */
public enum Direction {

    NORTH(-1, 0), SOUTH(1, 0), EAST(0, 1), WEST(0, -1);

    private final int deltaRow;
    private final int deltaColumn;

    /**
     * Getter of delta Row.
     *
     * @return deltaRow.
     */
    public int getDeltaRow() {
        return deltaRow;
    }

    /**
     * Constructor direction.
     *
     * @param deltaRow.
     * @param deltaColumn.
     */
    private Direction(int deltaRow, int deltaColumn) {
        this.deltaRow = deltaRow;
        this.deltaColumn = deltaColumn;
    }

    /**
     * getter of delta Column.
     *
     * @return deltaColumn.
     */
    public int getDeltaColumn() {
        return deltaColumn;
    }

    /**
     * The direction opposite to a given direction.
     *
     * @return direction opposite.
     */
    public Direction opposite() {
        Direction oppositeDirection =null;

        switch (this) {
            case NORTH:
                oppositeDirection = SOUTH;

                break;
            case SOUTH:
                oppositeDirection = NORTH;

                break;
            case EAST:
                oppositeDirection = WEST;

                break;
            case WEST:
                oppositeDirection = EAST;

                break;
                
               
        }

        return oppositeDirection;
    }
}
