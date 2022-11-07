package agh.ics.oop;


import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {

    private MoveDirection fromStr(String arg) {
        return switch (arg) {
            case "f", "forward" -> MoveDirection.FORWARD;
            case "b", "backward" -> MoveDirection.BACKWARD;
            case "r", "right" -> MoveDirection.RIGHT;
            case "l", "left" -> MoveDirection.LEFT;
            default -> null;
        };
    }

    public MoveDirection[] parse(String[] arguments) {
        return Arrays.stream(arguments)
                .map(this::fromStr)
                .filter(Objects::nonNull)
                .toArray(MoveDirection[]::new);
    }
}
