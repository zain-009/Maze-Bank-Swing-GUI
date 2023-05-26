import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class StudentLoanScreen extends Button implements ActionListener {
    JFrame studentFrame;
    Button tenThousandButton,fiftyThousandButton,getLoanButton,backButton;
    JTextField loanAmountTextfield;
    StudentLoanScreen(){
        studentFrame = new JFrame("Maze Bank");
        studentFrame.setLayout(null);

        tenThousandButton = new Button("10,000");
        tenThousandButton.addActionListener(this);
        fiftyThousandButton = new Button("50,000");
        fiftyThousandButton.addActionListener(this);
        getLoanButton = new Button("Get Loan");
        getLoanButton.addActionListener(this);
        backButton = new Button("Back");
        backButton.addActionListener(this);

        loanAmountTextfield = new JTextField();
        loanAmountTextfield.setFont(new Font("",Font.BOLD,18));

        tenThousandButton.setBounds(180,300,100,40);
        fiftyThousandButton.setBounds(500,300,100,40);
        loanAmountTextfield.setBounds(315,395,150,30);
        getLoanButton.setBounds(340,440,100,40);
        backButton.setBounds(650,500,100,30);


        studentFrame.add(HomeScreen.homeScreenImage);
        studentFrame.add(HomeScreen.spacer);
        studentFrame.add(HomeScreen.spacer1);
        studentFrame.add(tenThousandButton);
        studentFrame.add(fiftyThousandButton);
        studentFrame.add(getLoanButton);
        studentFrame.add(loanAmountTextfield);
        studentFrame.add(backButton);


        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        studentFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        studentFrame.setSize(800, 600);
        studentFrame.setVisible(true);
        studentFrame.setResizable(false);
        studentFrame.setIconImage(appIcon);
        studentFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            studentFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == tenThousandButton){
            if (Integer.parseInt(HomeScreen.activeLoan) < 40000){
                getLoan(10000);
            } else{
                JOptionPane.showMessageDialog(studentFrame,"Max Loan Allowed : 50,000\nfor Student Account");
            }
            studentFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == fiftyThousandButton){
            if (Integer.parseInt(HomeScreen.activeLoan) <= 0){
                getLoan(50000);
            } else {
                JOptionPane.showMessageDialog(studentFrame,"Max Loan Allowed : 50,000\nfor Student Account");
            }
            studentFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == getLoanButton){
            int loanAmount = Integer.parseInt(loanAmountTextfield.getText());
            if (loanAmount + Integer.parseInt(HomeScreen.activeLoan) > 50000){
                JOptionPane.showMessageDialog(studentFrame,"Max Loan Allowed : 50,000\nfor Student Account");
            } else {
                getLoan(loanAmount);
            }
            studentFrame.setVisible(false);
            new HomeScreen();
        }

    }
    void getLoan(int loanAmount){
        boolean loanOk = true;
        if (!String.valueOf(loanAmount).matches("\\d+")){
            loanOk = false;
        }
        if (loanOk){
            try {
                File file = new File("credentials/" + HomeScreen.activeGmail + ".txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith(HomeScreen.activeBalance)) {
                        line = String.valueOf(Integer.parseInt(HomeScreen.activeBalance) + loanAmount);
                    }
                    if ((line.startsWith(HomeScreen.activeLoan))){
                        line = String.valueOf(Integer.parseInt(HomeScreen.activeLoan) + loanAmount);
                    }
                    content.append(line).append(System.lineSeparator());
                }
                reader.close();
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                writer.write(content.toString());
                writer.close();
                JOptionPane.showMessageDialog(studentFrame,"Loan of Rs"+loanAmount+" Attained Successfully");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(studentFrame,"Enter Amount in Numbers!");
        }

    }
}
