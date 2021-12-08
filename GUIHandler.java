import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class GUIHandler extends JPanel {
	private int[] location;
	private JButton button, button2, button3;
	private JTextField locationX;
	private JTextField locationY;
	private JLabel column;
	private JLabel prompt;
	private JLabel row;

	public GUIHandler() {
		setPreferredSize(new Dimension(200, GamePanel.HEIGHT));
		setBackground(Color.darkGray);
		location = new int[2];
		location[0] = 10;
		location[1] = 10;
		
		add(panel1());
		add(panel2());
		add(panel3());

	}

	public int[] getLocationXY() {
		return location;
	}

	public class ButtonListener6 implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	Window[] windows = Window.getWindows();
        	for(Window window : windows){
        		window.dispose();
        	}
            MainMenu menu = new MainMenu();
        }
    }

    public class ButtonListener7 implements ActionListener{
        public void actionPerformed(ActionEvent e){
        	System.out.println("Player lost");
        	Window[] windows = Window.getWindows();
        	for(Window window : windows){
        		window.dispose();
        	}
            MainMenu menu = new MainMenu();
        }
    }

	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			location[0] = Integer.parseInt(locationX.getText()) - 1;
			location[1] = Integer.parseInt(locationY.getText()) - 1;
		}
	}

	private JPanel panel1() {
		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.darkGray);
		panel1.setLayout(new GridLayout(1, 0));
		panel1.setPreferredSize(new Dimension(180, 170));

		prompt = new JLabel();
		prompt.setText("Input coords below: ");
		prompt.setForeground(Color.white);
		prompt.setFont(prompt.getFont().deriveFont(14f));
		panel1.add(prompt);
		return panel1;
	}

	private JPanel panel2() {		
		JPanel panel2 = new JPanel();
		panel2.setBackground(Color.darkGray);
		panel2.setLayout(new GridLayout(2, 2));
		panel2.setPreferredSize(new Dimension(180, 60));

		row = new JLabel("Row: ");
		row.setForeground(Color.white);
		row.setFont(row.getFont().deriveFont(16f));
		locationX = new JTextField(1);
		panel2.add(row);
		panel2.add(locationX);

		column = new JLabel("Column: ");
		column.setForeground(Color.white);
		column.setFont(column.getFont().deriveFont(16f));
		locationY = new JTextField(1);
		panel2.add(column);
		panel2.add(locationY);
		return panel2;
	}

	private JPanel panel3() {
		JPanel panel3 = new JPanel();
		panel3.setBackground(Color.darkGray);
		panel3.setLayout(new GridLayout(0, 1));
		panel3.setPreferredSize(new Dimension(200, 100));

		button = new JButton("Submit");
		button.addActionListener(new ButtonListener());
		panel3.add(button);

		button3 = new JButton("Resign");
		button3.addActionListener(new ButtonListener7());
		panel3.add(button3);

		button2 = new JButton("Main Menu");
		button2.addActionListener(new ButtonListener6());
		panel3.add(button2);

		return panel3;
	}

}
