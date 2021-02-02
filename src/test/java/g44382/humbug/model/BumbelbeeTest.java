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

/**
 *
 * @author El Fahsi
 */
public class BumbelbeeTest {

    private Animal[] animals;
    private Board board;

    /**
     * Test of move method, of class Bumbelbee normal move.
     */
    @Test
    public void testMove() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),};
        System.out.println("move_general");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 2); //.next(Direction.EAST);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Bumbelbee move next case when final Square
     * is busy
     */
    @Test
    public void testMove_next_final_SquareBusy() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Ladybird(new Position(0, 2)),};
        System.out.println("move next case when final Square is busy");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Normal move on star
     */
    @Test
    public void testMove_next_onstar() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 1)),};
        System.out.println("move next on star");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(2, 1);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
        assertTrue(instance.isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class Bumbelbee he fall out .
     */
    @Test
    public void testMove_next_notinside_2() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),};
        System.out.println("move next case null");
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null; // move and fall
        Position result = instance.move(board, Direction.WEST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test move when the final position is empty or null
     */
    @Test
    public void testMove_next_When_Next_isNull() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        System.out.println("testMove_next_When_Next_isNull");
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Snail(new Position(0, 1)),
            new Grasshopper(new Position(0, 2)),};
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);

    }

    /**
     * Test move when the final position is not null but have to pass above null
     * square
     */
    @Test
    public void testMove_Fly_Above_null() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),},
            {null, new Square(GRASS), new Square(GRASS)},
            {new Square(GRASS), new Square(STAR), new Square(STAR)}
        });
        System.out.println("testMove_Fly_Above_null");
        animals = new Animal[]{
            new Bumbelbee(new Position(0, 0)),
            new Snail(new Position(0, 1)),
            new Grasshopper(new Position(0, 2)),};
        Bumbelbee instance = (Bumbelbee) animals[0];
        Position expResult = new Position(2, 0);
        Position result = instance.move(board, Direction.SOUTH, animals);
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
            new Bumbelbee(new Position(0, 0)),};
        System.out.println("test_When_animal_hasWall");
        Bumbelbee instance = (Bumbelbee) animals[0];
        board.getSquares()[instance.getPositionOnBoard().
                getRow()][instance.getPositionOnBoard().
                        getColumn()].setSouthWall(true);
        Position expResult = new Position(2, 0);
        Position result = instance.move(board, Direction.SOUTH, animals);
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
            new Bumbelbee(new Position(0, 0)),};
        Bumbelbee instance = (Bumbelbee) animals[0];

        board.getSquares()[instance.getPositionOnBoard().
                next(Direction.SOUTH).next(Direction.SOUTH).
                getRow()][instance.getPositionOnBoard().
                        next(Direction.SOUTH).next(Direction.SOUTH).
                        getColumn()].setNorthWall(true);
        Position expResult = new Position(2, 0);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }
}
