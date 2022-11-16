package agh.ics.oop;

import static java.lang.System.out;

public class World {


    public static void main(String[] argss) {
        out.println("system wystartowal");
        String[] args = {"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"};
        MoveDirection[] directions = new OptionParser().parse(args);
        GrassField map = new GrassField(10);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.println("system zakonczyl dzialanie");
    }
}