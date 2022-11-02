package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IntegrationTest {
    @Test
    public void firstTest(){
        String[] args = {"f", "b", "r", "l"};
        MoveDirection[] directions = new OptionParser().parse(args);
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(map.animals.get(0).getPosition(), new Vector2d(2,3));
        assertEquals(map.animals.get(0).getOrient(), MapDirection.EAST);
        assertEquals(map.animals.get(1).getPosition(), new Vector2d(3,3));
        assertEquals(map.animals.get(1).getOrient(), MapDirection.WEST);
    }

    @Test
    public void secondTest(){
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionParser().parse(args);
        RectangularMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        assertEquals(map.animals.get(0).getPosition(), new Vector2d(2,0));
        assertEquals(map.animals.get(0).getOrient(), MapDirection.SOUTH);
        assertEquals(map.animals.get(1).getPosition(), new Vector2d(3,5));
        assertEquals(map.animals.get(1).getOrient(), MapDirection.NORTH);
    }
}
