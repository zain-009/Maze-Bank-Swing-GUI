import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class BusinessLoanScreen extends Button implements ActionListener {
    JFrame businessFrame;
    Button oneLacButton,fiveLacButton,getLoanButton,backButton;
    JTextField loanAmountTextfield;
    BusinessLoanScreen(){
        businessFrame = new JFrame("Maze bank");
        businessFrame.setLayout(null);

        oneLacButton = new Button("100,000");
        oneLacButton.addActionListener(this);
        fiveLacButton = new Button("500,000");
        fiveLacButton.addActionListener(this);
        getLoanButton = new Button("Get Loan");
        getLoanButton.addActionListener(this);
        backButton = new Button("Back");
        backButton.addActionListener(this);

        loanAmountTextfield = new JTextField();
        loanAmountTextfield.setFont(new Font("",Font.BOLD,18));

        oneLacButton.setBounds(180,300,100,40);
        fiveLacButton.setBounds(500,300,100,40);
        loanAmountTextfield.setBounds(315,395,150,30);
        getLoanButton.setBounds(340,440,100,40);
        backButton.setBounds(650,500,100,30);

        businessFrame.add(HomeScreen.homeScreenImage);
        businessFrame.add(HomeScreen.spacer);
        businessFrame.add(HomeScreen.spacer1);
        businessFrame.add(oneLacButton);
        businessFrame.add(fiveLacButton);
        businessFrame.add(getLoanButton);
        businessFrame.add(loanAmountTextfield);
        businessFrame.add(backButton);


        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        businessFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        businessFrame.setSize(800, 600);
        businessFrame.setVisible(true);
        businessFrame.setResizable(false);
        businessFrame.setIconImage(appIcon);
        businessFrame.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backButton){
            businessFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == oneLacButton){
            if (Integer.parseInt(HomeScreen.activeLoan) < 400000){
                getLoan(100000);
            } else{
                JOptionPane.showMessageDialog(businessFrame,"Max Loan Allowed : 500,000\nfor Business Account");
            }
            businessFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == fiveLacButton){
            if (Integer.parseInt(HomeScreen.activeLoan) <= 0){
                getLoan(500000);
            } else {
                JOptionPane.showMessageDialog(businessFrame,"Max Loan Allowed : 500,000\nfor Business Account");
            }
            businessFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == getLoanButton){
            int loanAmount = Integer.parseInt(loanAmountTextfield.getText());
            if (loanAmount + Integer.parseInt(HomeScreen.activeLoan) > 500000){
                JOptionPane.showMessageDialog(businessFrame,"Max Loan Allowed : 500,000\nfor Business Account");
            } else {
                getLoan(loanAmount);
            }
            businessFrame.setVisible(false);
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
                JOptionPane.showMessageDialog(businessFrame,"Loan of Rs"+loanAmount+" Attained Successfully");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(businessFrame,"Enter Amount in Numbers!");
        }
    }
}
