package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class IntegrationTest {

    @Test
    public void test1() {
        Animal testAnimal = new Animal();
        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(testAnimal.getOrient(), MapDirection.EAST);
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(testAnimal.getOrient(), MapDirection.NORTH);
    }

    @Test
    public void test2() {
        Animal testAnimal = new Animal();
        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(testAnimal.getOrient(), MapDirection.EAST);
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(testAnimal.getOrient(), MapDirection.NORTH);
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.BACKWARD};
        String[] movesString = {"f", "right", "forward", "r", "right", "b"};
        assertArrayEquals((new OptionParser()).parse(movesString), moves);
    }

    @Test
    public void test3() {
        Animal testAnimal = new Animal();
        testAnimal.move(MoveDirection.RIGHT);
        assertEquals(testAnimal.getOrient(), MapDirection.EAST);
        testAnimal.move(MoveDirection.LEFT);
        assertEquals(testAnimal.getOrient(), MapDirection.NORTH);
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.BACKWARD};
        String[] movesString = {"f", "right", "forward", "r", "right", "b"};
        assertArrayEquals((new OptionParser()).parse(movesString), moves);
        assertEquals((new Vector2d(2, 2)), testAnimal.getPosition());
        for (MoveDirection move : moves) {
            testAnimal.move(move);
        }
        assertEquals((new Vector2d(4, 3)), testAnimal.getPosition());
        assertEquals(testAnimal.getOrient(), MapDirection.WEST);
    }
}
