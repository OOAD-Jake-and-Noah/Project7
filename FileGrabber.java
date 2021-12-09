import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

//this is the class that reads to and writes to the leaderboard text file. The way it works is that the file stores the top 5 leaderboard and when the
//read method is called the file is read and stored into an array. When trying to write a new name it is checked to see if it is is top 5, and if it is,
//the new name is inserted into the sorted array, which is then written to the textfile to update the leaderboard as the read happens every time the
//leaderboard is opened
public class FileGrabber{
     
    final static String fileLoc = "./leaderboardtext.txt";
    private JFrame frame = new JFrame("Leaderboard");
    private JPanel panel = new JPanel(new BorderLayout());
    private JTextArea textArea = new JTextArea(10,10);
    String[] top5 = new String[5];
    private JTextField textName;
    private JButton btn, btn2, btn3;
    private String newHighScore;
    private int highScoreInt;
    private JFrame f;
     
    public FileGrabber() {
         newHighScore = "00";
    }
     
    //the following method creates a new frame that displays the current leaderboard with two buttons 
    //to add a new name that takes the stored high score, or to return to the main menu
    //Leaderboard is stored in the following format 4 character name + whitespace + 2 digit number from 01-99
    public void readAndPrintTextFromFile() {
        btn2 = new JButton("Add New High Score");
        btn3 = new JButton("Return to Main Menu");
        btn2.setBounds(20,50,280,30);
        btn2.setBounds(20,70,280,30);
        btn2.addActionListener(new ButtonListener4());
        btn3.addActionListener(new ButtonListener5());
        String text;
        String testText = " ";
        int textLine = 0;
        textArea.setText(" ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try (BufferedReader br = new BufferedReader(new FileReader(fileLoc))) {
            while((text = br.readLine()) != null && textLine < 5) {
                    testText = text;
                    top5[textLine] = testText;
                    textArea.append("\n" + text);
                    textLine = textLine+1;
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
        textArea.setEditable(false); //make the leaderboard display non-editable
        panel.add(textArea, BorderLayout.PAGE_START);
        panel.add(btn3,BorderLayout.PAGE_END);
        panel.add(btn2);
        frame.add(panel);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setSize(300,300);
        frame.setVisible(true);
    }

    //the following will ask for a name to input and will write it to the file if it is top5
    //the score is passed to the function
    public void writeToFile(int scoreInt){
        String name;
        String score;
        f = new JFrame("New Leaderboard Entry");
        textName = new JTextField(4);
        textName.setBounds(20,50,280,30);
        btn = new JButton("Submit");
        btn.setBounds(100,140,100,40);
        btn.addActionListener(new ButtonListener3());
        f.add(textName);
        f.add(btn);
        f.setSize(340,250);
        f.setLayout(null);
        f.setVisible(true);
        if(scoreInt <= 9){
            score = " " + "0" + Integer.toString(scoreInt);
        }
        else{
            score = " " + Integer.toString(scoreInt);
        }
        newHighScore = score;
    }
    //this is used to return to the main menu
    public class ButtonListener5 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
            MainMenu menu = new MainMenu();
        }
    }
    //this closes the leaderboard window an opens the text input window for a new entry
    public class ButtonListener4 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
            writeToFile(highScoreInt);
        }
    }

    //waits for the submit on the write method to happen and when it does inserts the new name into the sorted array
    //if the score is high enough, and writes the new sorted leaderboard to the file and redisplays the leaderboard
    //when done
    public class ButtonListener3 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String name = textName.getText().substring(0,4);
            String result = "";
            int j;
            if(e.getSource() == btn){
                result = name + newHighScore;
            }
            for(j = 0; j <= 4; j++){
                if(Integer.parseInt(top5[j].substring(5,7)) < Integer.parseInt(result.substring(5,7)))
                    break;
            }
            for(int k = 5-2; k>=j; k--){
                top5[k+1] = top5[k];
            }
            if(j >= 5){
                j = 4;
            }
            top5[j] = result;
            int z = 0;
            try{
                FileWriter fw = new FileWriter(fileLoc);
                for (int i = 0; i < 10; i++){
                    if(i >= 4 && z <= 4){
                        fw.write(top5[z] + "\n");
                        z = z + 1;
                    }
                }
                fw.close();
            } catch(IOException g){
                System.out.println("An error occurred.");
                g.printStackTrace();
            }
            f.dispose();
            readAndPrintTextFromFile();   
        }
    }

    public void setHighScore(int newScore){
        highScoreInt = newScore;
    }
}