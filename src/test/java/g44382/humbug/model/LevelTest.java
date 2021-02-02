/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g44382.humbug.model;

import java.io.IOException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author El Fahsi
 */
public class LevelTest {

    private Board board;
    private Animal[] animals;

    public LevelTest() {
    }

    @Test
    public void testSomeMethod_Level_1() throws IOException {
        Game game = new Game();
        game.startLevel(1);
        animals = game.getAnimals();
        board = game.getBoard();
        Snail instance0 = (Snail) animals[0];
        Position temp = instance0.move(board, Direction.EAST, animals);
        instance0.setPositionOnBoard(temp);
        Position expResult = new Position(0, 1);
        assertEquals(expResult, instance0.getPositionOnBoard());

        temp = instance0.move(board, Direction.SOUTH, animals);
        instance0.setPositionOnBoard(temp);
        expResult = new Position(1, 1);
        assertEquals(expResult, instance0.getPositionOnBoard());

        temp = instance0.move(board, Direction.EAST, animals);
        instance0.setPositionOnBoard(temp);
        expResult = new Position(1, 2);
        assertEquals(expResult, instance0.getPositionOnBoard());

        temp = instance0.move(board, Direction.SOUTH, animals);
        instance0.setPositionOnBoard(temp);
        expResult = new Position(2, 2);
        assertEquals(expResult, instance0.getPositionOnBoard());
        assertTrue(instance0.isOnStar());

    }

    @Test
    public void testSomeMethod_Level_2() throws IOException {
        Game game = new Game();
        game.startLevel(2);
        animals = game.getAnimals();
        board = game.getBoard();
        Snail instance0 = (Snail) animals[0];
        Snail instance1 = (Snail) animals[1];
        Snail instance2 = (Snail) animals[2];

    }

    @Test
    public void testSomeMethod_Level_18() throws IOException {

        Game game = new Game();
        game.startLevel(18);
        animals = game.getAnimals();
        board = game.getBoard();
        Spider instance0 = (Spider) animals[0];
        Spider instance1 = (Spider) animals[1];
        Spider instance2 = (Spider) animals[2];

        Position temp = instance2.move(board, Direction.EAST, animals);
        instance2.setPositionOnBoard(temp);
        Position expResult = new Position(2, 3);
        assertEquals(expResult, instance2.getPositionOnBoard());

        temp = instance1.move(board, Direction.SOUTH, animals);
        instance1.setPositionOnBoard(temp);
        expResult = new Position(2, 0);
        assertEquals(expResult, instance1.getPositionOnBoard());

        temp = instance1.move(board, Direction.EAST, animals);
        instance1.setPositionOnBoard(temp);
        expResult = new Position(2, 2);
        assertEquals(expResult, instance1.getPositionOnBoard());

        temp = instance0.move(board, Direction.SOUTH, animals);
        instance0.setPositionOnBoard(temp);
        expResult = new Position(2, 0);
        assertEquals(expResult, instance0.getPositionOnBoard());

        temp = instance0.move(board, Direction.EAST, animals);
        instance0.setPositionOnBoard(temp);
        expResult = new Position(2, 1);
        assertEquals(expResult, instance0.getPositionOnBoard());
        assertTrue(instance0.isOnStar());

        temp = instance2.move(board, Direction.NORTH, animals);
        instance2.setPositionOnBoard(temp);
        expResult = new Position(0, 3);
        assertEquals(expResult, instance2.getPositionOnBoard());

        temp = instance2.move(board, Direction.WEST, animals);
        instance2.setPositionOnBoard(temp);
        expResult = new Position(0, 2);
        assertEquals(expResult, instance2.getPositionOnBoard());
        
        
        
        temp = instance1.move(board, Direction.NORTH, animals);
        instance1.setPositionOnBoard(temp);
        expResult = new Position(1, 2);
        assertEquals(expResult, instance1.getPositionOnBoard());
        assertTrue(instance1.isOnStar());
        
        temp = instance2.move(board, Direction.EAST, animals);
        instance2.setPositionOnBoard(temp);
        expResult = new Position(0, 4);
        assertEquals(expResult, instance2.getPositionOnBoard());
        assertTrue(instance2.isOnStar());

    }
}
