package agh.ics.oop;

import java.util.Arrays;

import static java.lang.System.out;

public class World {
    public static void run (Direction[] directions ) {
        for (Direction move: directions) {
            String message = switch (move) {
                case FORWARD -> "zwierzak idzie do przodu";
                case BACKWARD -> "zwierzak idzie do tylu";
                case RIGHT -> "zwierzak idzie w prawo";
                case LEFT -> "zwierzak idzie w lewo";
            };
            out.println(message);
        }
    }

    public static Direction[] convert (String[] args) {
        Direction[] moves = new Direction[args.length];
        int k=0;
        for (String arg : args) {
            if (!(arg.equals("f") || arg.equals("b") || arg.equals("r") || arg.equals("l"))) {
                continue;
            }
            switch (arg) {
                case "f" -> moves[k] = Direction.FORWARD;
                case "b" -> moves[k] = Direction.BACKWARD;
                case "r" -> moves[k] = Direction.RIGHT;
                case "l" -> moves[k] = Direction.LEFT;
            }
            k += 1;
        }
        return Arrays.copyOfRange(moves, 0, k);
    }

    public static void main(String[] args) {
        out.println("system wystartowal");
//        Direction[] moves = convert(args);
//        run(moves);

//        Animal dog = new Animal();
//        out.println(dog);
//        dog.move(MoveDirection.RIGHT);
//        dog.move(MoveDirection.FORWARD);
//        dog.move(MoveDirection.FORWARD);
//        dog.move(MoveDirection.FORWARD);
//        out.println(dog);
//        Animal cat  = new Animal();
//        out.println(cat);
//        MoveDirection[] catMoves = (new OptionParser()).parse(args);
//        for (MoveDirection catMove : catMoves) {
//            cat.move(catMove);
//        }
//        out.println(cat);

        MoveDirection[] directions = new OptionParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        out.println("system zakonczyl dzialanie");

    }
}