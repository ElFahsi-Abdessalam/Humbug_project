package g44382.humbug.model;

/**
 * Allows the movement of Spider in the game board.
 *
 * @author El Fahsi Abdessalam
 */
public class Spider extends Animal {

    /**
     * Construcor of spider.
     *
     * @param position checkPosition on board.
     */
    public Spider(Position position) {
        super(position);
    }

    /**
     * Constructor Default.
     */
    public Spider() {
        super();
    }

    /**
     * Advance an animal in the game and if it falls on a star the box is
     * transformed.
     *
     * @param board board in game.
     * @param direction direction to follow.
     * @param animals Array of animal.
     * @return position next in board.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        Position positionNext = spiderNextPosition(board, position,
                direction, animals);
        super.setPositionOnBoard(positionNext);
        if (positionNext == null) {
            setPositionOnBoard(null);
            return null;
        }
        return moveGeneral(board, positionNext, position, true, animals);
    }

    /**
     * scroll to the next table position
     *
     * @param board board game board
     * @param position position in the board
     * @param direction direction to follow
     * @param animals Array of animals
     * @return Position of spider
     */
    private Position spiderNextPosition(Board board, Position position,
            Direction direction,
            Animal... animals) {
        int type = 0;

        if (direction == Direction.EAST || direction == Direction.WEST) {
            type = board.getNbColumn();
        } else {
            type = board.getNbRow();

        }
        for (int i = 0; i < type; i++) {
            if (!checkAnimalPosition(position.next(direction), animals)) {
                return position;
            }
            position = position.next(direction);
            Position checkWallAnimals = null;
            int j = 0;
            while (j < animals.length) {
                checkWallAnimals = checkWallAnimals(board, j, direction, position, animals);
                if (checkWallAnimals != null) {
                    return checkWallAnimals;
                }
                j++;
            }
        }
        return null;
    }

    /**
     * Check animal and Wall
     *
     * @param board in game
     * @param j constant
     * @param direction direction on board
     * @param position position on board
     * @param animals array of animals
     * @return position
     */
    private Position checkWallAnimals(Board board, int j, Direction direction, Position position, Animal[] animals) {

        if (position.equals(animals[j].getPositionOnBoard())) {
            return position.next(direction.opposite());
        } else if (hasWall(board, direction) || hasWallOpposite(position, board, direction)) {
            return position.next(direction.opposite());
        } else if (hasWallDirection(board, direction, position)) {
            return position;
        } else if (hasWallOpposite(position, board, direction)) {
            return position.next(direction.opposite());
        }
        return null;
    }
}
