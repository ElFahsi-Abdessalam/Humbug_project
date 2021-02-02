package g44382.humbug.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static g44382.humbug.model.SquareType.GRASS;
import static g44382.humbug.model.SquareType.STAR;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import g44382.humbug.model.*;
import g44382.humbug.model.Direction;
import g44382.humbug.model.Position;
import g44382.humbug.model.Square;
//import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Pierre Bettens (pbt) <pbettens@he2b.be>
 */
public class SpiderTest {

    private Board board;
    private Animal[] animals;

    @BeforeEach
    public void setUp() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(1, 2))
        };
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove() {
        System.out.println("move and fall");
        Spider instance = (Spider) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_endline() {
        System.out.println("move end line and fall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        Spider instance = (Spider) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Spider.
     */
    @Test
    public void testMove_tootheranimal() {
        System.out.println("move to other animal");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals[1] = new Snail(new Position(0, 2));
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notfree() {
        System.out.println("move next case not free");
        Spider instance = (Spider) animals[0];
        animals[1].setPositionOnBoard(new Position(0, 1));
        Position expResult = new Position(0, 0); //don't move
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class Snail.
     */
    @Test
    public void testMove_next_notinside() {
        System.out.println("move next case null and fall");
        Spider instance = (Spider) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expResult, result);
    }

    @Test
    public void testMove_passOnStar() {
        System.out.println("move and pass on star without win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertFalse(animals[0].isOnStar());
        assertFalse(board.getSquareType(new Position(0, 1)) == GRASS);
    }

    @Test
    public void testMove_nextOnStar() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(STAR), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Spider instance = (Spider) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    @Test
    public void testMove_wallNorth() {
        System.out.println("wall north");
        Spider instance = (Spider) animals[0];
        board.getSquares()[instance.getPositionOnBoard().getRow()][instance
                .getPositionOnBoard().getColumn()].setNorthWall(true);
        Position expresult = new Position(0, 0);
        Position result = instance.move(board, Direction.NORTH, animals);
        assertEquals(expresult, result);
    }

    @Test
    public void testMove_wallEast() {
        System.out.println("wall east");
        Spider instance = (Spider) animals[0];
        board.getSquares()[instance.getPositionOnBoard().getRow()][instance
                .getPositionOnBoard().getColumn()].setEastWall(true);
        Position expresult = new Position(0, 0);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expresult, result);
    }

    @Test
    public void testMove_wallOppositeEast() {
        System.out.println("wall opposite");
        Spider instance = (Spider) animals[0];
        board.getSquares()[instance.getPositionOnBoard().next(Direction.EAST).getRow()][instance
                .getPositionOnBoard().next(Direction.EAST).getColumn()].setWestWall(true);
        Position expresult = new Position(0, 0);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expresult, result);
    }

    @Test
    public void wallOppositeSouth() {
        System.out.println("wall opposite");

        board = board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), null},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Spider(new Position(0, 1)),
            new Snail(new Position(1, 2))
        };
        Spider instance = (Spider) animals[0];
        board.getSquares()[instance.getPositionOnBoard().next(Direction.SOUTH).getRow()][instance
                .getPositionOnBoard().next(Direction.SOUTH).getColumn()].setNorthWall(true);
        Position expresult = new Position(0, 1);
        Position result = instance.move(board, Direction.SOUTH, animals);
        assertEquals(expresult, result);
    }

}
