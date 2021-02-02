package g44382.humbug.model;

/**
 * Allows the movement of LadyBird in the game board.
 *
 * @author El Fahsi Abdessalam
 */
public class Ladybird extends Animal {

    /**
     * Constructor of ladyBird.
     *
     * @param position position in board.
     */
    public Ladybird(Position position) {
        super(position);
    }

    /**
     * Constructor default.
     */
    public Ladybird() {
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
        Position positionNext = ladyBirdNextPosition(board, position, direction, animals);
        if (positionNext == null) {
            setPositionOnBoard(null);
            return null;
        }
        return moveGeneral(board, positionNext, position, true, animals);
    }

    /**
     * check nextPosition.
     *
     * @param board board in game.
     * @param position in board.
     * @param direction of animal NORTH SOUTH EAST WEST.
     * @param animals array of animals.
     * @return instance is the next position of ladyBird.
     */
    private Position ladyBirdNextPosition(Board board, Position position, Direction direction, Animal... animals) {
        Position instance = ladybirdFree(board, position, direction, animals);
        boolean ladybirdNull = ladybirdNull(board, position.next(direction), direction);
        if (ladybirdNull) {
            return null;
        }
        if (instance != null) {
            return instance;
        }

        position = position.next(direction).next(direction);
        if (isInside(board, position)) {
            if (checkAnimalPosition(position, animals)) {
                instance = position;
            } else {
                instance = super.getPositionOnBoard().next(direction);
            }
            if (hasWallOpposite(position, board, direction)) {
                return super.getPositionOnBoard().next(direction);
            }
            if (hasWallDirection(board, direction, position)) {
                instance = position;
            }
        }
        return instance;
    }

    /**
     * check next case if free.
     *
     * @param board board in game.
     * @param position in board.
     * @param direction of animal NORTH SOUTH EAST WEST.
     * @param animals array of animals.
     * @return a position if there there a wall.
     */
    private Position ladybirdFree(Board board, Position position, Direction direction, Animal... animals) {
        position = position.next(direction);
        if (isInside(board, position)) {
            if (hasWall(board, direction) || hasWallOpposite(position, board, direction)) {
                return super.getPositionOnBoard();
            }
            if (hasWallDirection(board, direction, position)) {
                return position;
            }
            if (!checkAnimalPosition(position, animals)) {
                return super.getPositionOnBoard();
            }
        }
        return null;
    }
    /**
     * check is the next Position is null
     * @param board board in game
     * @param position position on board
     * @param direction direction on board
     * @return boolean 
     */
    private boolean ladybirdNull(Board board, Position position, Direction direction) {
        return !isInside(board, position) && isInside(board, position.next(direction));
    }
}
