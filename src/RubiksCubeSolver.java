import java.util.Scanner;
public class RubiksCubeSolver {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RubiksCube cube = new RubiksCube();
        cube.display();

        while (true) {
            System.out.print("Command: ");
            String command = scanner.nextLine().toUpperCase();
            if (command.equals("EX")) {
                System.out.println("Exiting THE PROGRAM...");
                break;
            } else if (command.equals("RS")) {
                cube.shuffle();
                cube.display();
            } else if (command.matches("[ULRFB][+-]?")) {
                cube.rotateLayer(command);
                cube.display();
                if (cube.isSolved()) {
                    System.out.println("Congratulations!..You solved the Rubik's Cube!");
                    break;
                }
            } else {
                System.out.println("Invalid command");
            }
        }
        scanner.close();
    }

}
