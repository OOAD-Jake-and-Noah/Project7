import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

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
          GameEngine.getInstance().generateFleet(false);
          boolean player = true;
          boolean inGame = true;
          while (inGame) {
            System.out.println();
            System.out.println((player) ? "\n---- Player 1's Turn ----\n" : "\n---- Player 2's Turn ----\n");
            System.out.println((!player) ? "Player 1's Board:" : "Player 2's Board:");
            GameEngine.getInstance().printBoard(!player, false);
            System.out.println("Choose a coordinate to fire at (i.e. B10): ");
            boolean[] result = GameEngine.getInstance().fireShot(!player, (player) ? new Coordinate(input.next()) : new Coordinate(ThreadLocalRandom.current().nextInt(1,11), ThreadLocalRandom.current().nextInt(1,11)));
            while (!result[0]) {
              if (player) {
                System.out.println("Error: You've already shot there! Try again...");
                System.out.println("Choose a coordinate to fire at (i.e. B10): ");
              }
              result = GameEngine.getInstance().fireShot(!player, (player) ? new Coordinate(input.next()) : new Coordinate(ThreadLocalRandom.current().nextInt(1,11), ThreadLocalRandom.current().nextInt(1,11)));
            }
            System.out.println((result[1]) ? "HIT!" : "MISS!");
            if (result[2]) {
              System.out.println("A SHIP HAS BEEN SUNK!");
              if (GameEngine.getInstance().fleetSunk(!player)) {
                System.out.println((player ? "CONGRATULATIONS! ENEMY" : "SORRY! YOUR") + " FLEET HAS BEEN SUNK!");
                inGame = false;
              }
            }
            System.out.println((!player) ? "Player 1's Board" : "Player 2's Board");
            GameEngine.getInstance().printBoard(!player, false);
            player = !player;
          }
          break;
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
