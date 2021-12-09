import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.Random;

//parts taken from existing JSwing Battleship: https://github.com/aawantika/battleship-gui
//however this has been modified to fit our needs as we have a seperate game engine
public class GamePanel extends JPanel {
	public static final int WIDTH = 600, HEIGHT = 600;
	public static int numOfShipsFound = 0;

	private Box box;
	private Box[][] boxes;
	private GUIHandler cPanel;
	private Rectangle bounds;
	private Timer timer;

	public GamePanel(GUIHandler control) {
		cPanel = control;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.gray);
		bounds = new Rectangle(0, 0, WIDTH, HEIGHT);
		boxes = new Box[10][10];
		createBoxes(boxes);
		createBattleship(boxes);

		timer = new Timer(100, new TimerListener());
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boxes[i][j].draw(g);
			}
		}	
	}

	private class TimerListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			checkLocation();
			repaint();
		}
	}

	public void createBoxes(Box[][] boxes) {
		int x = 120;
		int y = 120;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				boxes[i][j] = new Box(x, y);
				x = x + 40;
			}
			y = y + 40;
			x = 120;
		}
	}

	public void createBattleship(Box[][] boxes) {
			boxes[0][2].isBattleship();
			boxes[0][3].isBattleship();
			boxes[0][4].isBattleship();
			boxes[4][5].isBattleship();
			boxes[5][5].isBattleship();
			boxes[7][5].isBattleship();
			boxes[7][7].isBattleship();
			boxes[7][6].isBattleship();
	}

	public void checkLocation() {
		int[] locationXY = cPanel.getLocationXY();
		int xPos = locationXY[0];
		int yPos = locationXY[1];

		if ((xPos != 10) && (yPos != 10)) {
			if (boxes[xPos][yPos].getBattleship()) {
				boxes[xPos][yPos].hitBox();
			} else {
				boxes[xPos][yPos].emptyBox();
			}
		} 
	}
}
