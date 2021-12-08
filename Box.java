import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class Box {
	private boolean battleship;
	private Color c;
	private int x, y;

	public Box(int xPos, int yPos) {
		x = xPos;
		y = yPos;
		c = Color.black;
		battleship = false;
	}

	public void draw(Graphics g) {
		g.setColor(c);
        g.fillRect(x, y, 35, 35);
    }

    public void isBattleship() {
    	battleship = true;
    }

    public boolean getBattleship() {
        return battleship;
    }

    public void emptyBox() {
        c = Color.white;
    }
    
    public void hitBox() {
        c = Color.red;
    }

    public Color getColor() {
    	return c;
    } 
}
