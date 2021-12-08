import java.util.Arrays;
import java.util.Scanner;

public class Driver {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean running = true;
    while (running) {
      System.out.println("\nChoose an option #: ");
      System.out.println("\t1: Play Vs Bot");
      System.out.println("\t2: Play 2-Player");
      System.out.println("\t3: Leaderboard");
      System.out.println("\t4: Exit\n");
      int menuChoice = input.nextInt();
      switch (menuChoice) {
        case 1:
          System.out.println("Starting game vs a bot...\n");
          GameEngine.getInstance().generateFleet(true);
          while (true) {
            GameEngine.getInstance().printBoard(true, true);
            System.out.println();
            GameEngine.getInstance().printBoard(true, false);
            System.out.println("Choose a coordinate to fire at (i.e. B10): ");
            boolean[] result = GameEngine.getInstance().fireShot(true, new Coordinate(input.next()));
            if (result[0]) {
              System.out.println((result[1]) ? "HIT!" : "MISS!");
            }
            else {
              System.out.println("Error: You've already shot there! Try again...");
            }
          }
          //break;
        case 2:
          System.out.println("Starting 2-player game...\n");
          System.out.println("Error: Game mode not supported yet!");
          break;
        case 3:
          System.out.println("Opening leaderboard...\n");
          System.out.println("---------------------------");
          System.out.println("--- NO SCORES AVAILABLE ---");
          System.out.println("---------------------------");
          break;
        case 4:
          System.out.println("Exiting application...\n");
          running = false;
          break;
        case 0:
          System.out.println("Entering testing mode...\n");
          break;
        default:
          System.out.println("Invalid selection, try again!");
          break;
      }
    }
  }
}
