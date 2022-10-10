package agh.ics.oop;

public class World {

    public static Direction[] stringToEnum(String[] args) {
        Direction[] result;
        result = new Direction[args.length];
        for(int i=0; i<args.length; i++) {
            result[i] = switch(args[i]) {
                case "f" -> Direction.FORWARD;
                case "b" -> Direction.BACKWARD;
                case "r" -> Direction.RIGHT;
                case "l" -> Direction.LEFT;
                default -> null;
            };
        }
        return result;
    }

    public static void run(Direction[] directions) {
//        System.out.println(String.join(", ", directions));
        for(Direction direction : directions) {
            String messageEnd = switch(direction) {
                case FORWARD -> "idzie do przodu";
                case BACKWARD -> "idzie do tyłu";
                case RIGHT -> "skręca w prawo";
                case LEFT -> "skręca w lewo";
            };
            System.out.println("Zwierzak " + messageEnd);
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");
        run(stringToEnum(args));
        System.out.println("Stop");
    }
}
