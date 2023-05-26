import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoanScreen extends Button implements ActionListener {
    JFrame loanFrame;
    Button getLoanButton,payLoanbutton,backButton;
    LoanScreen(){
        loanFrame = new JFrame("Maze Bank");
        loanFrame.setLayout(null);

        getLoanButton = new Button("Get Loan");
        getLoanButton.addActionListener(this);
        payLoanbutton = new Button("Pay Loan");
        payLoanbutton.addActionListener(this);
        backButton = new Button("Back");
        backButton.addActionListener(this);

        backButton.setBounds(650,500,100,30);
        getLoanButton.setBounds(200,300,150,60);
        payLoanbutton.setBounds(450,300,150,60);

        loanFrame.add(HomeScreen.homeScreenImage);
        loanFrame.add(HomeScreen.spacer);
        loanFrame.add(HomeScreen.spacer1);
        loanFrame.add(backButton);
        loanFrame.add(getLoanButton);
        loanFrame.add(payLoanbutton);

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        loanFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loanFrame.setSize(800, 600);
        loanFrame.setVisible(true);
        loanFrame.setResizable(false);
        loanFrame.setIconImage(appIcon);
        loanFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == getLoanButton){
            if (HomeScreen.activeAccountType.equals("Student")){
                new StudentLoanScreen();
                loanFrame.setVisible(false);
            }
            if (HomeScreen.activeAccountType.equals("Business")){
                new BusinessLoanScreen();
                loanFrame.setVisible(false);
            }
        }
        if (e.getSource() == payLoanbutton){
            if (Integer.parseInt(HomeScreen.activeLoan) > 0){
                payLoan();
                loanFrame.setVisible(false);
                new HomeScreen();
            } else {
                JOptionPane.showMessageDialog(loanFrame,"No Pending Loan");
            }
        }
        if (e.getSource() == backButton){
            loanFrame.setVisible(false);
            new HomeScreen();
        }
    }
    void payLoan(){
        int payableLoan = Integer.parseInt(JOptionPane.showInputDialog(loanFrame,"Enter amount of Loan to Pay\nPending Loan : "+HomeScreen.activeLoan));
        boolean loanOk = true;
        if (!String.valueOf(payableLoan).matches("\\d+")){
            loanOk = false;
        }
        if (loanOk){
            if (payableLoan >= 10000){
                if (payableLoan <= Integer.parseInt(HomeScreen.activeLoan)){
                    try {
                        File file = new File("credentials/" + HomeScreen.activeGmail + ".txt");
                        BufferedReader reader = new BufferedReader(new FileReader(file));
                        StringBuilder content = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.startsWith(HomeScreen.activeBalance)){
                                line = String.valueOf(Integer.parseInt(HomeScreen.activeBalance) - payableLoan);
                            }
                            if (line.startsWith(HomeScreen.activeLoan)) {
                                line = String.valueOf(Integer.parseInt(HomeScreen.activeLoan) - payableLoan);
                            }
                            content.append(line).append(System.lineSeparator());
                        }
                        reader.close();
                        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                        writer.write(content.toString());
                        writer.close();
                        JOptionPane.showMessageDialog(loanFrame,"Loan of Rs"+payableLoan+" Payed Successfully");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                } else {
                    JOptionPane.showMessageDialog(loanFrame,"Cannot Pay More Than Loan Amount");
                }
            } else {
                JOptionPane.showMessageDialog(loanFrame,"Minimum amount of Payable\nLoan at once : Rs 10,0000");
            }
        } else {
            JOptionPane.showMessageDialog(loanFrame,"Enter Amount in Numbers!");
        }
    }
}
