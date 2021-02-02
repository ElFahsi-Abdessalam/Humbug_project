package g44382.humbug.model;

/**
 * The position class allows you to identify yourself in the game with the rows
 * and columns
 *
 * @author El Fahsi Abdessalam
 */
public class Position {

    private final int row;
    private final int column;

    /**
     * Construcor default.
     */
    public Position() {
        this.row = 0;
        this.column = 0;
    }

    /**
     * Display row and column.
     *
     * @return string of characters.
     */
    @Override
    public String toString() {
        return "Position{" + "row=" + row + ", column=" + column + '}';
    }

    /**
     * Constructor of Position.
     *
     * @param row of position.
     * @param column of position.
     */
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * Simple getter of Row.
     *
     * @return row of table.
     */
    public int getRow() {
        return row;
    }

    /**
     * Simple getter of column.
     *
     * @return column table.
     */
    public int getColumn() {
        return column;
    }

    /**
     * Returns a hash code value for the object. This method is supported for
     * the benefit of hash tables such as those provided by HashMap.
     *
     * @return hash a hash code value for this object.
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.row;
        hash = 37 * hash + this.column;
        return hash;
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare.
     *
     * @return object true if this object is the same as the obj argument; false
     * otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Position other = (Position) obj;
        if (this.row != other.row) {
            return false;
        }
        return this.column == other.column;
    }

    /**
     * @param direction direction NORTH,SOUTH,EAST,WEST.
     * @return position a position next on the board.
     */
    public Position next(Direction direction) {
        return new Position(direction.getDeltaRow()
                + row, direction.getDeltaColumn() + column);
    }
}
