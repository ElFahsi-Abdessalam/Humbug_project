package g44382.humbug.model;

/**
 *
 * Allows the movement of Grasshopper in the game board.
 *
 *
 * @author El Fahsi Abdessalam
 */
public class Grasshopper extends Animal {

    /**
     * Constructor default.
     */
    public Grasshopper() {
        super();
    }

    /**
     * Constructor of Grasshopper.
     *
     * @param position in board.
     */
    public Grasshopper(Position position) {
        super(position);
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
        Position positionNext = checkAnimal(board, position.next(direction), direction, animals);

        boolean checkAnimalPosition = checkAnimalPosition(position.next(direction),
                animals);

        if (!isInside(board, positionNext)) {
            super.setPositionOnBoard(null);
            return positionNext = null;
        }
        return moveGeneral(board, positionNext, position, checkAnimalPosition, animals);

    }
}
