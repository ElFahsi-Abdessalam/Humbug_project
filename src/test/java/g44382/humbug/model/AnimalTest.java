/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g44382.humbug.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author El Fahsi
 */
public class AnimalTest {
    /**
     * Test of opposite
     */
    @Test
    public void opposite() {
        System.out.println("has opposire wall");
        Direction instance = Direction.SOUTH;
        Direction expResult = Direction.NORTH;
        Direction result = instance.opposite();
        assertEquals(expResult, result);
    }

   
}
