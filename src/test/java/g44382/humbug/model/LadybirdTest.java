/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g44382.humbug.model;

import static g44382.humbug.model.SquareType.GRASS;
import static g44382.humbug.model.SquareType.STAR;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

/**
 *
 * @author El Fahsi
 */
public class LadybirdTest {
    
      private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),
            new Ladybird(new Position(0, 1)),
            new Ladybird(new Position(2, 0)),
            new Ladybird(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class LadyBird normal move.
     */
    @Test
    public void testMove() {
       
        System.out.println("move_general");
        Ladybird instance = (Ladybird) animals[0];
        animals[1].setPositionOnBoard(new  Position(1, 1));
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class LadyBird move next case when final Square
     * is busy
     */
    @Test
    public void testMove_next_final_SquareBusy() {
        
        System.out.println("move next case when final Square is busy");
        Ladybird instance = (Ladybird) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 2));
        Position expResult = new Position(0, 1); //don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Normal move on star
     */
    @Test
    public void testMove_next_onstar() {
      
        System.out.println("move next on star");
        Ladybird instance = (Ladybird) animals[1];
        Position expResult = new Position(2, 1);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class LadyBird he fall out .
     */
    @Test
    public void testMove_next_notinside_2() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),};
        System.out.println("move next case null");
        Ladybird instance = (Ladybird) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test when animal has wall on the east
     */
    @Test
    public void test_When_animal_hasWall() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),};
        System.out.println("test_When_animal_hasWall");
        Ladybird instance = (Ladybird) animals[0];
        board.getSquares()[instance.getPositionOnBoard().
                getRow()][instance.getPositionOnBoard().
                        getColumn()].setEastWall(true);
        Position expResult = new Position(0, 0);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test when animal has wall on the opposition direction
     */
    @Test
    public void test_When_animal_hasWall_Opposite_Direction() {
        System.out.println("test_When_animal_hasWall_OppositeDirection");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Ladybird(new Position(0, 0)),};
        Ladybird instance = (Ladybird) animals[0];

        board.getSquares()[instance.getPositionOnBoard().
                next(Direction.EAST).next(Direction.EAST).
                getRow()][instance.getPositionOnBoard().
                        next(Direction.EAST).next(Direction.EAST).
                        getColumn()].setWestWall(true);
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
}
