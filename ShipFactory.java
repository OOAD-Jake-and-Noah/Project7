import java.util.concurrent.ThreadLocalRandom;

// Factory pattern used to generate Ships of the various subclass types
public class ShipFactory {
  // Creates a new ship of a given type, while ensuring it doesn't overlap with any existing Ships
  public static Ship createShip(String type, Ship[] existingShips) {
    for (int h=0; h<10; h++) {
      Coordinate upperLeft = new Coordinate(ThreadLocalRandom.current().nextInt(1,11), ThreadLocalRandom.current().nextInt(1,11));
      boolean horiz = ThreadLocalRandom.current().nextBoolean();
      Ship ship = null;
      switch (type) {
        case "AircraftCarrier":
          ship = new AircraftCarrier(upperLeft, horiz);
          break;
        case "Battleship":
          ship = new Battleship(upperLeft, horiz);
          break;
        case "Destroyer":
          ship = new Destroyer(upperLeft, horiz);
          break;
        case "Submarine":
          ship = new Submarine(upperLeft, horiz);
          break;
        case "PatrolBoat":
          ship = new PatrolBoat(upperLeft, horiz);
          break;
      }
      boolean valid = ship.positions[ship.length-1].getX() <= 10 && ship.positions[ship.length-1].getY() <= 10;
      for (int i=0; i<existingShips.length && valid; i++) {
        if (existingShips[i] != null && ship.overlaps(existingShips[i])) valid = false;
      }
      if (valid) return ship;
    }
    return null;
  }

  // Creates a whole fleet of Ships with one of each of the subtypes
  public static Ship[] createFleet() {
    String[] types = {"AircraftCarrier", "Battleship", "Destroyer", "Submarine", "PatrolBoat"};
    Ship[] ships = {null, null, null, null, null};
    for (int i=0; i<5; i++) {
      Ship newShip = createShip(types[i], ships);
      if (newShip != null) {
        ships[i] = newShip;
      }
      else {
        i = -1;
        ships = new Ship[] {null, null, null, null, null};
      }
    }
    return ships;
  }
}
