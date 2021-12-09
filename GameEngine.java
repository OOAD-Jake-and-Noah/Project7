import java.util.Observable;
import java.util.Observer;

public class GameEngine {
  private static GameEngine myInstance = new GameEngine();

  public static GameEngine getInstance() {
    return myInstance;
  }

  private GameEngine() {}

  private Ship[] fleetP1;
  private Coordinate[] missedP1 = new Coordinate[100];
  private Ship[] fleetP2;
  private Coordinate[] missedP2 = new Coordinate[100];
  private int score = 0;

  public void generateFleet(boolean player1) {
    this.score = 0;
    System.out.println((player1) ? "Player 1 Fleet:" : "Player 2 Fleet:");
    if (player1) {
      this.fleetP1 = ShipFactory.createFleet();
    }
    else {
      this.fleetP2 = ShipFactory.createFleet();
    }
    this.printBoard(player1, true);
  }

  public int[][] makeBoard(boolean player1) {
    Ship[] fleet = (player1) ? this.fleetP1 : this.fleetP2;
    Coordinate[] misses = (player1) ? this.missedP1 : this.missedP2;
    int[][] board = new int[11][11];
    for (int i=0; i<misses.length && misses[i] != null; i++) {
      int x = misses[i].getX();
      int y = misses[i].getY();
      board[y][x] = 'O';
    }
    for (int i=0; i<fleet.length; i++) {
      Ship ship = fleet[i];
      boolean sunk = ship.isSunk();
      for (int j=0; j<ship.length; j++) {
        Coordinate c = ship.positions[j];
        int x = c.getX();
        int y = c.getY();
        if (sunk) {
          board[y][x] = '*';
        }
        else {
          board[y][x] = (ship.hits[j]) ? 'X' : ship.getClass().toString().charAt(6);;
        }
      }
    }
    return board;
  }

  public void printBoard(boolean player1, boolean showHidden) {
    int[][] board = makeBoard(player1);
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
            char id = (char)board[y][x];
            if (showHidden || id == 'O' || id == '*' || id == 'X') {
              System.out.print(id);
            }
          }
          System.out.print(' ');
        }
        System.out.print("\t| ");
      }
      System.out.println("\n_________________________________________________________________________________________");
      System.out.println("                                                                                         ");
    }
  }

  // {validShot, wasAHit, sunkAShip}
  public boolean[] fireShot(boolean atPlayer1, Coordinate target) {
    boolean[] invalidShot = {false, false, false};
    boolean[] validMiss = {true, false, false};
    boolean[] validHit = {true, true, false};
    boolean[] validSunk = {true, true, true};
    Ship[] fleet = (atPlayer1) ? this.fleetP1 : this.fleetP2;
    Coordinate[] misses = (atPlayer1) ? this.missedP1 : this.missedP2;
    int k=0;
    for (; k<misses.length && misses[k] != null; k++) {
      if (misses[k].equals(target)) {
        return invalidShot;
      }
    }
    for (int i=0; i<fleet.length; i++) {
      Ship ship = fleet[i];
      if (ship.contains(target)) {
        for (int j=0; j<ship.length; j++) {
          Coordinate c = ship.positions[j];
          if (c.equals(target)) {
            if (ship.hits[j]) {
              return invalidShot;
            }
            ship.hits[j] = true;
            this.score += 6;
            return (ship.isSunk() ? validSunk : validHit);
          }
        }
      }
    }
    misses[k] = target;
    this.score -= 1;
    return validMiss;
  }

  public boolean fleetSunk(boolean player1) {
    Ship[] fleet = (player1) ? this.fleetP1 : this.fleetP2;
    for (int i=0; i<fleet.length; i++) {
      if (!fleet[i].isSunk()) {
        return false;
      }
    }
    return true;
  }

  public int getScore() {
    int s = this.score;
    this.score = 0;
    return (s > 99) ? 99 : s;
  }

}
