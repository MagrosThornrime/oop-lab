package agh.ics.oop;

public class World {

    public static Direction[] stringToEnum(String[] args) {
        Direction[] result = new Direction[args.length];
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
            if(direction == null) {
                continue;
            }
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

        MapDirection direction = MapDirection.NORTH;

        System.out.println("Start");
        run(stringToEnum(args));
        System.out.println("Stop");

        Vector2d position1 = new Vector2d(1,2);
        System.out.println(position1);
        Vector2d position2 = new Vector2d(-2,1);
        System.out.println(position2);
        System.out.println(position1.add(position2));

        System.out.println(direction);
        System.out.println(direction.next());
        System.out.println(direction.previous());
        System.out.println(direction.toUnitVector());
    }
}
