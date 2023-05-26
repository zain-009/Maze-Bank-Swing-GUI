import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class WithdrawScreen extends Button implements ActionListener {
    JFrame withdrawFrame;
    Button fiveHundredButton,oneThousandButton,twoThousandButton,threeThousandButton,fourThousandButton,fiveThousandButton,cancelButton,withdrawButton;
    JTextField withdrawAmountTextfield;


    WithdrawScreen(){
        withdrawFrame = new JFrame("Maze Bank");
        withdrawFrame.setLayout(null);

        withdrawAmountTextfield = new JTextField();
        withdrawAmountTextfield.setFont(new Font("",Font.BOLD,18));

        fiveHundredButton = new Button("Rs500");
        fiveHundredButton.addActionListener(this);
        oneThousandButton = new Button("Rs1000");
        oneThousandButton.addActionListener(this);
        twoThousandButton = new Button("Rs2000");
        twoThousandButton.addActionListener(this);
        threeThousandButton = new Button("Rs3000");
        threeThousandButton.addActionListener(this);
        fourThousandButton = new Button("Rs4000");
        fourThousandButton.addActionListener(this);
        fiveThousandButton = new Button("Rs5000");
        fiveThousandButton.addActionListener(this);
        withdrawButton = new Button("Withdraw");
        withdrawButton.addActionListener(this);
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);

        fiveHundredButton.setBounds(140,225,100,40);
        threeThousandButton.setBounds(140,300,100,40);
        oneThousandButton.setBounds(340,225,100,40);
        fourThousandButton.setBounds(340,300,100,40);
        twoThousandButton.setBounds(540,225,100,40);
        fiveThousandButton.setBounds(540,300,100,40);
        withdrawButton.setBounds(340,425,100,40);
        cancelButton.setBounds(650,500,100,30);
        withdrawAmountTextfield.setBounds(315,385,150,30);

        withdrawFrame.add(HomeScreen.spacer);
        withdrawFrame.add(HomeScreen.spacer1);
        withdrawFrame.add(HomeScreen.homeScreenImage);
        withdrawFrame.add(fiveHundredButton);
        withdrawFrame.add(oneThousandButton);
        withdrawFrame.add(twoThousandButton);
        withdrawFrame.add(threeThousandButton);
        withdrawFrame.add(fourThousandButton);
        withdrawFrame.add(fiveThousandButton);
        withdrawFrame.add(withdrawButton);
        withdrawFrame.add(withdrawAmountTextfield);
        withdrawFrame.add(cancelButton);

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        withdrawFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        withdrawFrame.setSize(800, 600);
        withdrawFrame.setVisible(true);
        withdrawFrame.setResizable(false);
        withdrawFrame.setIconImage(appIcon);
        withdrawFrame.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton){
            withdrawFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == withdrawButton){
            try {
                int withdrawAmount = Integer.parseInt(withdrawAmountTextfield.getText());
                if (withdrawAmount > Integer.parseInt(HomeScreen.activeBalance)){
                    JOptionPane.showMessageDialog(withdrawFrame,"Insufficient Balance");
                    withdrawAmountTextfield.setText("");
                } else if (withdrawAmount >= 500){
                    withdraw(withdrawAmount);
                    withdrawFrame.setVisible(false);
                    new HomeScreen();
                } else {
                    JOptionPane.showMessageDialog(withdrawFrame,"Minimum Withdrawal Amount : 500");
                    withdrawAmountTextfield.setText("");
                }
            } catch (NumberFormatException x){
                JOptionPane.showMessageDialog(withdrawFrame,"Please Enter Amount in Numbers");
            }
        }
        if (e.getSource() == fiveHundredButton){
            if (500 > Integer.parseInt(HomeScreen.activeBalance)){
                JOptionPane.showMessageDialog(withdrawFrame,"Insufficient Balance");
            } else {
                withdraw(500);
                withdrawFrame.setVisible(false);
                new HomeScreen();
            }
        }
        if (e.getSource() == oneThousandButton){
            if (1000 > Integer.parseInt(HomeScreen.activeBalance)){
                JOptionPane.showMessageDialog(withdrawFrame,"Insufficient Balance");
            } else {
                withdraw(1000);
                withdrawFrame.setVisible(false);
                new HomeScreen();
            }
        }
        if (e.getSource() == twoThousandButton){
            if (2000 > Integer.parseInt(HomeScreen.activeBalance)){
                JOptionPane.showMessageDialog(withdrawFrame,"Insufficient Balance");
            } else {
                withdraw(2000);
                withdrawFrame.setVisible(false);
                new HomeScreen();
            }
        }
        if (e.getSource() == threeThousandButton){
            if (3000 > Integer.parseInt(HomeScreen.activeBalance)){
                JOptionPane.showMessageDialog(withdrawFrame,"Insufficient Balance");
            } else {
                withdraw(3000);
                withdrawFrame.setVisible(false);
                new HomeScreen();
            }
        }
        if (e.getSource() == fourThousandButton){
            if (4000 > Integer.parseInt(HomeScreen.activeBalance)){
                JOptionPane.showMessageDialog(withdrawFrame,"Insufficient Balance");
            } else {
                withdraw(4000);
                withdrawFrame.setVisible(false);
                new HomeScreen();
            }
        }
        if (e.getSource() == fiveThousandButton){
            if (5000 > Integer.parseInt(HomeScreen.activeBalance)){
                JOptionPane.showMessageDialog(withdrawFrame,"Insufficient Balance");
            } else {
                withdraw(5000);
                withdrawFrame.setVisible(false);
                new HomeScreen();
            }
        }
    }
    void withdraw(int withdrawAmount){
        try {
            File file = new File("credentials/" + HomeScreen.activeGmail + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(HomeScreen.activeBalance)) {
                    line = String.valueOf(Integer.parseInt(HomeScreen.activeBalance) - withdrawAmount);
                }
                content.append(line).append(System.lineSeparator());
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();
            JOptionPane.showMessageDialog(withdrawFrame,"Withdrawn $"+withdrawAmount+" Successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
