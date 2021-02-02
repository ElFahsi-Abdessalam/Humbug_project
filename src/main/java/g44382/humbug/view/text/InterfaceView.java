package g44382.humbug.view.text;

import g44382.humbug.model.Animal;
import g44382.humbug.model.Board;
import g44382.humbug.model.Direction;
import g44382.humbug.model.Position;

/**
 * InterfaceView
 *
 * @author El Fahsi Abdessalam
 */
public abstract interface InterfaceView {

    /**
     * Abstract méthode displayBoard
     *
     * @param board baord
     * @param animals
     */
    void displayBoard(Board board, Animal... animals);

    /**
     * Abstract method askPosition
     *
     * @return Position 
     */
    Position askPosition();

    /**
     * Abstract askDirection
     *
     * @return Direction of baord
     */
    Direction askDirection();

    /**
     * Abstract displayError
     *
     * @param message
     */
    void displayError(String message);
    /**
     * Display remaing Move
     * @param remaining 
     */
    void displayRemainingMoves(int remaining);
    /**
     *  Abstract displayLevel
     * @param nLevel 
     */
    void displayLevel(int nLevel);
    
    /**
     * Abstract Display Humbug
     */
    void displayHumbug();
    
  
 
}
