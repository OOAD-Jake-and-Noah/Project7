import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class MainMenu{
	JButton b1,b3,b4;
	JFrame frame;
	MainMenu(){
	frame = new JFrame("Main Menu");


	frame.setPreferredSize(new Dimension(500, 200));
	frame.getContentPane().setBackground(Color.darkGray);

	JButton b1 = new JButton("New Game");
	JButton b3 = new JButton("Leaderboard");
	JButton b4 = new JButton("Quit");

	b1.addActionListener(new ButtonListener1());
	b3.addActionListener(new ButtonListener2());
	b4.addActionListener(e->System.exit(0));

	b1.setBounds(50,50,120,50);
	b3.setBounds(180,50,120,50);
	b4.setBounds(310,50,120,50);

	frame.add(b1);
	frame.add(b3);
	frame.add(b4);

	frame.setSize(300,200);

	frame.setLayout(null);
	frame.pack();
	frame.setVisible(true);

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}

	public class ButtonListener1 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			System.out.println("test");
			frame.dispose();
			JFrame gameBoard = new JFrame("Game Board Test");
			gameBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			GUIHandler control = new GUIHandler();
			gameBoard.add(control, BorderLayout.WEST);
			gameBoard.add(new GamePanel(control));
			gameBoard.pack();
			gameBoard.setVisible(true);
		}
	}

	public class ButtonListener2 implements ActionListener{
		public void actionPerformed(ActionEvent e){
			frame.dispose();
			FileGrabber leaderboard = new FileGrabber();
			leaderboard.readAndPrintTextFromFile();
		}
	}


}
