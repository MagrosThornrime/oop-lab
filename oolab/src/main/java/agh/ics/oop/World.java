package agh.ics.oop;

public class World {

    public static MoveDirection[] stringToEnum(String[] args) {
        // ta metoda jest do usunięcia, bo przeszła do OptionsParser
        MoveDirection[] result = new MoveDirection[args.length];
        for(int i=0; i<args.length; i++) {
            result[i] = switch(args[i]) {
                case "f" -> MoveDirection.FORWARD;
                case "b" -> MoveDirection.BACKWARD;
                case "r" -> MoveDirection.RIGHT;
                case "l" -> MoveDirection.LEFT;
                default -> null;
            };
        }
        return result;
    }

    public static void run(MoveDirection[] directions) {
//        System.out.println(String.join(", ", directions));
        for(MoveDirection direction : directions) {
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

        //lab1
//        System.out.println("Start");
//        run(stringToEnum(args));
//        System.out.println("Stop");

        //lab2
//        MapDirection direction = MapDirection.NORTH;
//
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));
//
//        System.out.println(direction);
//        System.out.println(direction.next());
//        System.out.println(direction.previous());
//        System.out.println(direction.toUnitVector());

        //lab3
//        Animal dog = new Animal();
//        System.out.println(dog);
//        dog.move(MoveDirection.RIGHT);
//        System.out.println(dog);
//        dog.move(MoveDirection.FORWARD);
//        dog.move(MoveDirection.FORWARD);
//        System.out.println(dog);
//        dog.move(MoveDirection.LEFT);
//        for(int i=0; i<4; i++){
//            dog.move(MoveDirection.FORWARD);
//        }
//        System.out.println(dog);
//        dog.move(MoveDirection.LEFT);
//        dog.move(MoveDirection.FORWARD);
//        System.out.println(dog);
//        for(int i=0; i<6; i++){
//            dog.move(MoveDirection.FORWARD);
//        }
//        System.out.println(dog);

        Animal dog = new Animal();
        System.out.println(dog);
        OptionsParser parser = new OptionsParser();
        String[] directions = {"f", "f", "r", "b", "l", "b", "b", "b", "b", "b"};
        for(MoveDirection arg: parser.parse(directions)) {
            dog.move(arg);
            System.out.println(dog);
        }
    }
}
