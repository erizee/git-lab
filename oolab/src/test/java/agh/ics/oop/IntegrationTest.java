package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class IntegrationTest {
    @Test
    public void firstTest(){
        String[] args = {"f", "b", "r", "l"};
        MoveDirection[] directions = new OptionParser().parse(args);
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(map.animalsList.get(0).getPosition(), new Vector2d(2,3));
        assertEquals(map.animalsList.get(0).getOrient(), MapDirection.EAST);
        assertEquals(map.animalsList.get(1).getPosition(), new Vector2d(3,3));
        assertEquals(map.animalsList.get(1).getOrient(), MapDirection.WEST);
    }

    @Test
    public void secondTest(){
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionParser().parse(args);
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(map.animalsList.get(0).getPosition(), new Vector2d(2,0));
        assertEquals(map.animalsList.get(0).getOrient(), MapDirection.SOUTH);
        assertEquals(map.animalsList.get(1).getPosition(), new Vector2d(3,5));
        assertEquals(map.animalsList.get(1).getOrient(), MapDirection.NORTH);
    }

    @Test
    public void thirdTest() {
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(map.animalsList.get(0).getPosition(), new Vector2d(2,0));
        assertEquals(map.animalsList.get(0).getOrient(), MapDirection.SOUTH);
        assertEquals(map.animalsList.get(1).getPosition(), new Vector2d(3,7));
        assertEquals(map.animalsList.get(1).getOrient(), MapDirection.NORTH);
    }

    @Test
    public void parserTest() {
        MoveDirection[] moves = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.RIGHT, MoveDirection.BACKWARD};
        String[] movesString = {"f", "right", "forward", "x", "r", "right", "b"};
        assertThrows(IllegalArgumentException.class, () -> (new OptionParser()).parse(movesString));

        String[] movesString2 = {"f", "right", "forward", "r", "right", "b"};
        assertArrayEquals((new OptionParser()).parse(movesString2), moves);
    }

    @Test
    public void placeTest() {
        String[] args = {"f", "f"};
        MoveDirection[] directions = new OptionParser().parse(args);
        GrassField map = new GrassField(10);
        map.place(new Animal(map, new Vector2d(2, 2)));
        assertThrows(IllegalArgumentException.class, () -> map.place(new Animal(map, new Vector2d(2, 2))));
    }
}
