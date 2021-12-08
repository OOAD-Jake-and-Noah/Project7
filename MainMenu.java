import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class MainMenu{
	MainMenu(){
	JFrame frame = new JFrame("Main Menu");


	frame.setPreferredSize(new Dimension(500, 200));
	frame.getContentPane().setBackground(Color.darkGray);

	JButton b1 = new JButton("New Game");
	JButton b2 = new JButton("Options");
	JButton b3 = new JButton("Leaderboard");
	JButton b4 = new JButton("Quit");

	b4.addActionListener(e->System.exit(0));

	b1.setBounds(50,50,90,50);
	b2.setBounds(150,50,90,50);
	b3.setBounds(250,50,90,50);
	b4.setBounds(350,50,90,50);

	frame.add(b1);
	frame.add(b2);
	frame.add(b3);
	frame.add(b4);

	frame.setSize(300,200);

	frame.setLayout(null);
	frame.pack();
	frame.setVisible(true);

	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
