package agh.ics.oop;
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
    public static void main(String[] args) {
        out.println("system wystartowal");
        Direction[] moves = new Direction[args.length];
        for (int i=0; i<args.length; i++) {
            if (!(args[i].equals("f") || args[i].equals("b") || args[i].equals("r") || args[i].equals("l"))) {
                out.println("niepoprawny ruch");
                out.println("system zakonczyl dzialanie");
                return;
            }
            moves[i] = switch (args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> Direction.FORWARD;
            };

        }
        run(moves);
        out.println("system zakonczyl dzialanie");
    }
}