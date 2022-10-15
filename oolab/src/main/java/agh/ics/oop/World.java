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
        Direction[] moves = convert(args);
        out.println(Arrays.toString(moves));
        run(moves);
        out.println("system zakonczyl dzialanie");
    }
}