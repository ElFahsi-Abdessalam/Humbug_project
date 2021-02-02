package g44382.humbug.model;

/**
 * The Snail class represents a snail. The snail moves one space in the
 * direction indicated on condition that the space is not occupied by another
 * animal
 *
 * @author El Fahsi Abdessalam
 */
public class Snail extends Animal {

    /**
     * Constructor snail.
     *
     * @param position in board.
     */
    public Snail(Position position) {
        super(position);
    }

    /**
     * Constructor default
     */
    public Snail() {
        super();
    }

    /**
     *
     * Check if the position of the animal is outside the table. Change the
     * value of star to grass if an animal passes on the square.
     *
     * @param board is board game.
     * @param direction direction the animal will take.
     * @param animals animal table of animals.
     * @return positionNext next position of animal.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        Position positionNext = position.next(direction);
        boolean checkAnimalPosition = checkAnimalPosition(position.next(direction),
                animals);

        return moveSnail(board, direction, position, positionNext, animals);
    }

    private Position moveSnail(Board board, Direction direction, Position position, Position positionNext, Animal... animals) {

        if (isInside(board, positionNext) && hasWall(board, direction) || hasWallOpposite(positionNext, board, direction)) {
            return position;
        } else if (!checkAnimalPosition(positionNext, animals)) {
            return position;
        }
        if (!isInside(board, positionNext) && hasWall(board, direction)) {
            setPositionOnBoard(position);
            return getPositionOnBoard();
        } 
        return moveGeneral(board, positionNext, position, true, animals);

    }

}
