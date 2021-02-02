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
 * @author El Fahsi Abdessalam
 */
public class GrasshopperTest {

    private Board board;
    private Animal[] animals;

    /**
     * Test of move method, of class GrassHopper when next Position is out of
     * the board
     */
    @Test
    public void testMove() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 3)),
            new Snail(new Position(1, 2))
        };
        System.out.println("move and fall");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = null; // fall
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class GrassHopper next Position is also out of
     * the board
     */
    @Test
    public void testMove_endline() {

        System.out.println("move end line and fall");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 3)),
            new Snail(new Position(1, 2))
        };

        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = null;
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class GrassHopper normal move without animal on
     * the next Square
     */
    @Test
    public void testMove_NormalMove() {
        System.out.println("move normal");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Snail(new Position(1, 2))
        };
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class GrassHopper have a jump on one animal
     */
    @Test
    public void testMove_next_Jump_One_Animal() {

        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Snail(new Position(0, 1))
        };
        System.out.println("move next jump from on animal");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 2); // move
        Position result = instance.move(board, Direction.EAST, animals);
        System.out.println(result.getRow());
        System.out.println(result.getColumn());
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class GrassHopper jump on two animal
     */
    @Test
    public void testMove_next_Two_jump() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS)},
            {null, null, new Square(STAR)}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Snail(new Position(0, 1)),
            new Snail(new Position(0, 2))
        };
        System.out.println("move next case null and fall");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 3);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * Test of move method, of class GrassHopper jump normal on a star
     */
    @Test
    public void testMove_OnStar() {
        System.out.println("move  on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(STAR), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * Test of move method, of class GrassHopper apply a jump on animal to the
     * star
     */
    @Test
    public void testMove_nextOnStar2() {
        System.out.println("move, next on star and win");
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(STAR),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Snail(new Position(0, 1))
        };
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 2);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
        assertTrue(animals[0].isOnStar());
        assertEquals(GRASS, board.getSquareType(result));
    }

    /**
     * test When square Has Wall_On_East
     */
    @Test
    public void testWhenHasWall_On_East() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Snail(new Position(0, 3))
        };
        System.out.println("Test when has wall on east");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
        board.getSquares()[expResult.getRow()][expResult.getColumn()]
                .setEastWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }

    /**
     * test When square Has Wall_On_Opposite_Direction
     */
    @Test
    public void testWhenHasWall_On_Opposite_Direction() {
        board = new Board(new Square[][]{
            {new Square(GRASS), new Square(GRASS), new Square(GRASS),
                new Square(GRASS)},
            {null, new Square(GRASS), new Square(GRASS), null},
            {null, null, new Square(STAR), null}
        });
        animals = new Animal[]{
            new Grasshopper(new Position(0, 0)),
            new Snail(new Position(0, 2))
        };
        System.out.println("Test when has wall on opposite direction");
        Grasshopper instance = (Grasshopper) animals[0];
        Position expResult = new Position(0, 1);
        board.getSquares()[expResult.next(Direction.EAST)
                .getRow()][expResult.next(Direction.EAST).getColumn()]
                .setWestWall(true);
        Position result = instance.move(board, Direction.EAST, animals);
        assertEquals(expResult, result);
    }
}
