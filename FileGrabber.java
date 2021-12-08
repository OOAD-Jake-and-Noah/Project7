import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FileGrabber {
     
    final static String fileLoc = "C:\\Users\\Noah\\Documents\\GitHub\\Project6\\leaderboardtext.txt";
    JFrame frame = new JFrame("Leaderboard");
    JTextArea textArea = new JTextArea(10,10);
     
    public FileGrabber() {
         
    }
     
    public void readAndPrintTextFromFile() {
        String text;
        textArea.setText(" ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try (BufferedReader br = new BufferedReader(new FileReader(fileLoc))) {
            while((text = br.readLine()) != null) {
                    textArea.append("\n" + text);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setSize(300,300);
        frame.getContentPane().add(textArea, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}