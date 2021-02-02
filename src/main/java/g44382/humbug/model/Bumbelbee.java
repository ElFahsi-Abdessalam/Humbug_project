package g44382.humbug.model;
/**
 * Allows the movement of bumbelbee in the game board.
 *
 *
 * @author El Fahsi Abdessalam
 */
public class Bumbelbee extends Animal {
    /**
     * Constructor of bumbelbee.
     *
     * @param position in board.
     */
    public Bumbelbee(Position position) {
        super(position);
    }
    /**
     * Constructor default.
     */
    public Bumbelbee() {
        super();
    }
    /**
     *
     * Check if a bumbelbee can move on the game board.
     *
     * @param board is board game.
     * @param direction direction the animal will take.
     * @param animals array of animal.
     * @return positionNext next position of animal.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        Position positionNext = checkAnimal(board, position.next(direction).next(direction), direction, animals);
        boolean checkAnimalPosition = checkAnimalPosition(position, animals);
        if (positionNext == null) {
            super.setPositionOnBoard(null);
            return null;
        }
        return moveGeneral(board, positionNext, position, checkAnimalPosition, animals);
    }
}
