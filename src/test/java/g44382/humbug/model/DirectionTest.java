/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g44382.humbug.model;

import static g44382.humbug.model.Direction.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author El Fahsi Abdessalam
 */
public class DirectionTest {
    

    @Test
    public void testOpposite() {
        System.out.println("Test opposite");
        Direction instance = NORTH;
        Direction expResult = SOUTH;
        Direction result = instance.opposite();
         assertEquals(expResult, result);
    }   
}
