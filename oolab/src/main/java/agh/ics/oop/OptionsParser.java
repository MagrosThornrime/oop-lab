package agh.ics.oop;


import java.util.Arrays;
import java.util.Objects;

public class OptionsParser {

    private MoveDirection fromStr(String arg) {
        return switch(arg) {
            case "f" -> MoveDirection.FORWARD;
            case "b" -> MoveDirection.BACKWARD;
            case "r" -> MoveDirection.RIGHT;
            case "l" -> MoveDirection.LEFT;
            default -> null;
        };
    }

    public MoveDirection[] parse(String[] arguments) {
        return Arrays.stream(arguments)
                .filter(Objects::nonNull)
                .filter(s -> s.matches("^[fblr]$"))
                .map(this::fromStr)
                .toArray(MoveDirection[]::new);
    }
}
