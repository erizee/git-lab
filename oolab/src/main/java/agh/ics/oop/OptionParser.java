package agh.ics.oop;

import java.util.Arrays;

public class OptionParser {

    final String[] legal = {"f", "forward", "b", "backward", "r", "right", "l", "left"};

    public MoveDirection[] parse(String[] args) {
        MoveDirection[] moves = new MoveDirection[args.length];
        int k=0;
        for (String arg : args) {
            switch (arg) {
                case "f", "forward" -> moves[k] = MoveDirection.FORWARD;
                case "b", "backward" -> moves[k] = MoveDirection.BACKWARD;
                case "r", "right" -> moves[k] = MoveDirection.RIGHT;
                case "l", "left" -> moves[k] = MoveDirection.LEFT;
                default -> throw new IllegalArgumentException(arg+" is not legal move specification");
            }
            k += 1;
        }
        return Arrays.copyOfRange(moves, 0, k);
    }
}
