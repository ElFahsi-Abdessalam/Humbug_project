package g44382.humbug.model;

import java.io.IOException;

/**
 *
 * @author El Fahsi
 */
public interface Model {

    /**
     * Interface implemente Game
     *
     * @return baord
     */
    Board getBoard();

    /**
     * method getAnimal to be defined
     *
     * @return table of animal
     */
    Animal[] getAnimals();
    /**
     * method RemainingMoves  to be defined
     * 
     * @return RemainingMoves
     */
    int getRemainingMoves();

    /**
     * method start level to be defined
     *
     * @param level of game ends
     */
    void startLevel(int level) throws IOException;
    /**
     * method getLevelStatus  to be defined
     * @return  LevelStatut
     */
    LevelStatus getLevelStatus();

    /**
     * method move to be defined
     *
     * @param position position on board
     * @param direction direction north south east west
     */
    void move(Position position, Direction direction);

}
