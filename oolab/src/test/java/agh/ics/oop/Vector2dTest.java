package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Vector2dTest{
    @Test
    public void toStringTest(){
        assertEquals((new Vector2d(21,12)).toString(), "(21,12)");
    }
    @Test
    public void precedesTest(){
        assertTrue((new Vector2d(10,10)).precedes(new Vector2d(100,100)));
    }
    @Test
    public void followsTest(){
        assertTrue((new Vector2d(100,100)).follows(new Vector2d(10,10)));
    }
    @Test
    public void addTest(){
        assertEquals((new Vector2d(1,1)).add(new Vector2d(2,2)), new Vector2d(3,3));
    }
    @Test
    public void subtractTest(){
        assertEquals((new Vector2d(4,4)).subtract(new Vector2d(0,7)), new Vector2d(4,-3));
    }
    @Test
    public void upperRightTest(){
        assertEquals((new Vector2d(100,10)).upperRight(new Vector2d(0,105)), new Vector2d(100,105));
    }
    @Test
    public void lowerLeftTest(){
        assertEquals((new Vector2d(12,21)).lowerLeft(new Vector2d(44,1)), new Vector2d(12,1));
    }

    @Test
    public void oppositeTest(){
        assertEquals((new Vector2d(8,7)).opposite(), new Vector2d(-8,-7));
    }
    @Test
    public void equalsTest(){
        assertEquals((new Vector2d(12, 21)), new Vector2d(12, 21));
    }
    @Test
    public void hashCodeTest(){
        assertEquals((new Vector2d(1, 1)).hashCode(), 15511);
    }
}