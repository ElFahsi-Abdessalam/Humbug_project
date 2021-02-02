/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g44382.humbug.model;

/**
 * Allows the movement of butterfly in the game board.
 *
 * @author El Fahsi Abdessalam
 */
public class Butterfly extends Animal {

    /**
     * Construcot of butterfly.
     *
     * @param position in board.
     */
    public Butterfly(Position position) {
        super(position);
    }
    /**
     * Constructor default.
     */
      public Butterfly() {
        super();
    }

    /**
     *
     * Check if a butterfly can move on the game board.
     *
     * @param board is board game.
     * @param direction direction the animal will take.
     * @param animals array of animal.
     * @return positionNext next position of animal.
     */
    @Override
    public Position move(Board board, Direction direction, Animal... animals) {
        Position position = super.getPositionOnBoard();
        Position positionNext = checkAnimal(board, position.next(direction).next(direction).next(direction), direction, animals);
        boolean checkAnimalPosition = checkAnimalPosition(position, animals);
        if (positionNext == null) {
            super.setPositionOnBoard(null);
            return null;
        }
        return moveGeneral(board, positionNext, position, checkAnimalPosition, animals);
    }
        
      

}
