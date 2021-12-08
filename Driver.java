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
          Ship[] playerFleet = ShipFactory.createFleet();
          int[][] board = new int[11][11];
          for (int i=0; i<playerFleet.length; i++) {
            Ship ship = playerFleet[i];
            System.out.println(ship);
            for (int j=0; j<ship.length; j++) {
              Coordinate c = ship.positions[j];
              int x = c.getX();
              int y = c.getY();
              board[y][x] = ship.getClass().toString().charAt(6);
            }
          }
          for (int y=0; y<=10; y++) {
            for (int x=0; x<=10; x++) {
              Coordinate c = new Coordinate(x,y);
              if (x == 0 && y == 0){
                System.out.print('-');
              }
              else if (y == 0) {
                System.out.print(x);
              }
              else if (x == 0) {
                System.out.print((char)(y + 'A' - 1));
              }
              else {
                if (board[y][x] != 0){
                  System.out.print((char)board[y][x]);
                }
                System.out.print(' ');
              }
              System.out.print("\t| ");
            }
            System.out.println("\n_________________________________________________________________________________________");
            System.out.println("                                                                                         ");
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
