import java.util.Arrays;

public class Ship {
  public Coordinate[] positions;
  public boolean[] hits;
  public int length;

  public Ship(Coordinate pos, boolean isHorizontal, int length) {
    this.length = length;
    this.hits = new boolean[length];
    for (int i=0; i<length; i++) {
      this.hits[i] = false;
    }
    this.positions = new Coordinate[length];
    int x = pos.getX();
    int y = pos.getY();
    for(int i=0; i<length; i++) {
      this.positions[i] = new Coordinate(x, y);
      if (isHorizontal) {
        x += 1;
      }
      else {
        y += 1;
      }
    }
  }

  public boolean isSunk() {
    for (int i=0; i<length; i++) {
      if (!this.hits[i]) {
        return false;
      }
    }
    return true;
  }

  public String toString() {
    return (this.getClass() + Arrays.toString(this.positions)).substring(6);
  }

  public boolean overlaps(Ship other) {
    for (int i=0; i<this.length; i++) {
      for (int j=0; j<other.length; j++) {
        if (this.positions[i].equals(other.positions[j])) {
          return true;
        }
      }
    }
    return false;
  }

  public boolean contains(Coordinate coord) {
    for (int i=0; i<this.length; i++) {
      if (this.positions[i].equals(coord)) {
        return true;
      }
    }
    return false;
  }
}
