// Class to help with managing the 2D coordinate system used in Battleship
public class Coordinate {
  private String value;
  private int x;
  private int y;

  public Coordinate(String val) {
    this.value = val;
    this.x = Integer.valueOf(val.substring(1));
    this.y = val.charAt(0) - 'A' + 1;
  }
  public Coordinate(int x_pos, int y_pos) {
    this.x = x_pos;
    this.y = y_pos;
    this.value = (char)(y_pos + 'A' - 1) + Integer.toString(x);
  }

  public String get() {
    return this.value;
  }

  public int getX() {
    return this.x;
  }
  public int getY() {
    return this.y;
  }

  public String toString() {
    return this.get();
  }

  public boolean equals(Coordinate other) {
    return this.value.equals(other.value);
  }
}
