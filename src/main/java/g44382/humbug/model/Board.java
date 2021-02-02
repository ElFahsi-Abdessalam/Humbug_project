package g44382.humbug.model;

import static g44382.humbug.model.SquareType.GRASS;
import static g44382.humbug.model.SquareType.STAR;

/**
 * Board is the board consisting of boxes.
 *
 * The board only displays boxes with a value other than null.
 *
 * @author El Fahsi Abdessalam.
 */
public class Board {

    private Square[][] squares;

    /**
     * Constructor default.
     */
    public Board() {
    }

    /**
     * Construcor board.
     *
     * @param squares square is a box of board.
     */
    Board(Square[][] squares) {
        this.squares = squares;
    }

    /**
     * change value on Grass.
     *
     * @param position position.
     */
    protected void setOnGrass(Position position) {
        this.squares[position.getRow()][position.getColumn()].setType(GRASS);
    }

    /**
     * Getter of square.
     *
     * @return square.
     */
    public Square[][] getSquares() {
        return squares;
    }

    /**
     *
     * Check position in row and column table.
     *
     * @param position position to verify.
     * @return true or false if positionRow and positionColumn is Grass or Star.
     */
    public boolean isInside(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Position is null");
        }
        if (position.getRow() < 0 || position.getRow() > squares.length - 1) {
            return false;
        }
        if (position.getColumn() < 0 || position.getColumn()
                > squares[position.getRow()].length - 1) {
            return false;
        }
        if (squares[position.getRow()][position.getColumn()] == null) {
            return false;
        }

        return squares[position.getRow()][position.getColumn()].getType() != null;
    }

    /**
     * Check if the position of the array exists.
     *
     * @param position in board.
     * @return squares.
     */
    public SquareType getSquareType(Position position) {
        if (!isInside(position)) {
            throw new IllegalArgumentException("Position does not exist or null ");
        }

        return squares[position.getRow()][position.getColumn()].getType();
    }

    /**
     * Getter of nbRow.
     *
     * @return square length.
     */
    public int getNbRow() {
        return squares.length;
    }

    /**
     * getter of nbColumn.
     *
     * @return nbColumn.
     */
    public int getNbColumn() {
        return squares[0].length;
    }

}
