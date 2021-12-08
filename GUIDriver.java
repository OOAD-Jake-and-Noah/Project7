import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GUIDriver {
	public static void main(String[] args) {
		MainMenu menu = new MainMenu();
		JFrame frame = new JFrame("Game Board Test");
		FileGrabber test = new FileGrabber();
		test.readAndPrintTextFromFile();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GUIHandler control = new GUIHandler();
		frame.add(control, BorderLayout.WEST);
		frame.add(new GamePanel(control));
		frame.pack();
		frame.setVisible(true);
	}
}