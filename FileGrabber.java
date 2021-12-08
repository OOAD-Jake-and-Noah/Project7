import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class FileGrabber{
     
    final static String fileLoc = "./leaderboardtext.txt";
    private JFrame frame = new JFrame("Leaderboard");
    private JPanel panel = new JPanel(new BorderLayout());
    private JTextArea textArea = new JTextArea(10,10);
    String[] top5 = new String[5];
    private JTextField textName;
    private JButton btn, btn2, btn3;
    private String newHighScore;
    private JFrame f;
     
    public FileGrabber() {
         newHighScore = "00";
    }
     
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
        for(int i = 0; i < 5; i++){
            System.out.println(top5[i]);
            System.out.println(top5[i].substring(0,4));
            System.out.println(top5[i].substring(5,7));
        }
        panel.add(textArea, BorderLayout.PAGE_START);
        panel.add(btn3,BorderLayout.PAGE_END);
        panel.add(btn2);
        frame.add(panel);
        frame.setPreferredSize(new Dimension(300, 300));
        frame.setSize(300,300);
        //frame.getContentPane().add(textArea, BorderLayout.CENTER);
        //frame.pack();
        frame.setVisible(true);
    }

    public void writeToFile(int scoreInt){
        String name;
        String score;
        f = new JFrame("New Leaderboard Entry");
        textName = new JTextField();
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

    public class ButtonListener5 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
            MainMenu menu = new MainMenu();
        }
    }

    public class ButtonListener4 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            frame.dispose();
            writeToFile(10);
        }
    }

    public class ButtonListener3 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            String name = textName.getText();
            String result = "";
            int j;
            if(e.getSource() == btn){
                result = name + newHighScore;
            }
            for(j = 0; j <= 4; j++){
                if(Integer.parseInt(top5[j].substring(5,7)) < Integer.parseInt(result.substring(5,7)))
                    break;
            }
            System.out.println("test");
            for(int k = 5-2; k>=j; k--){
                top5[k+1] = top5[k];
            }
            System.out.println(j);
            if(j >= 5){
                j = 4;
            }
            top5[j] = result;
            System.out.println("Top 5 Leaderboard Values: ");
            for(int x = 0; x < 5; x++){
                System.out.println(top5[x]);
            }

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
}