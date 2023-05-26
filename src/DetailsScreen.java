import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DetailsScreen extends Button implements ActionListener {
    JFrame detailsFrame;
    Button changePinButton, closeAccountButton,backButton;
    JPanel spacer1;
    JLabel usernameLabel,genderLabel,cnicLabel,gmailLabel,balanceLabel,accountTypeLabel,pinLabel,loanLabel;
    DetailsScreen() {
        detailsFrame = new JFrame("Maze Bank");
        detailsFrame.setLayout(null);

        spacer1 = new JPanel();
        spacer1.setBackground(new Color(237,58,61,255));
        spacer1.setLayout(null);

        usernameLabel = new JLabel("Account Holder Name : " + HomeScreen.activeUsername);
        usernameLabel.setFont(new Font("",Font.BOLD,16));
        genderLabel = new JLabel("Gender                        : " + HomeScreen.activeGender);
        genderLabel.setFont(new Font("",Font.BOLD,16));
        cnicLabel = new JLabel("Cnic                             : " + HomeScreen.activeCnic);
        cnicLabel.setFont(new Font("",Font.BOLD,16));
        gmailLabel = new JLabel("Gmail                           : " + HomeScreen.activeGmail);
        gmailLabel.setFont(new Font("",Font.BOLD,16));
        balanceLabel = new JLabel("Current Balance          : Rs " + HomeScreen.activeBalance);
        balanceLabel.setFont(new Font("",Font.BOLD,16));
        accountTypeLabel = new JLabel("Account Type              : " + HomeScreen.activeAccountType);
        accountTypeLabel.setFont(new Font("",Font.BOLD,16));
        pinLabel = new JLabel("Pin                               : " + HomeScreen.activePin);
        pinLabel.setFont(new Font("",Font.BOLD,16));
        loanLabel = new JLabel("Pending Loan              : " + HomeScreen.activeLoan);
        loanLabel.setFont(new Font("",Font.BOLD,16));

        changePinButton = new Button("Change Pin");
        changePinButton.addActionListener(this);
        closeAccountButton = new Button("Close Account");
        closeAccountButton.addActionListener(this);
        backButton = new Button("Back");
        backButton.addActionListener(this);

        usernameLabel.setBounds(40,220,400,30);
        genderLabel.setBounds(40,250,400,30);
        cnicLabel.setBounds(40,280,400,30);
        gmailLabel.setBounds(40,310,400,30);
        balanceLabel.setBounds(40,340,400,30);
        accountTypeLabel.setBounds(40,370,400,30);
        pinLabel.setBounds(40,400,400,30);
        loanLabel.setBounds(40,430,400,30);
        spacer1.setBounds(30,140,725,50);
        changePinButton.setBounds(40,480,100,30);
        closeAccountButton.setBounds(200,480,120,30);
        backButton.setBounds(650,500,100,30);

        detailsFrame.add(HomeScreen.homeScreenImage);
        detailsFrame.add(HomeScreen.spacer);
        detailsFrame.add(usernameLabel);
        detailsFrame.add(genderLabel);
        detailsFrame.add(cnicLabel);
        detailsFrame.add(gmailLabel);
        detailsFrame.add(balanceLabel);
        detailsFrame.add(accountTypeLabel);
        detailsFrame.add(pinLabel);
        detailsFrame.add(loanLabel);
        detailsFrame.add(spacer1);
        detailsFrame.add(changePinButton);
        detailsFrame.add(closeAccountButton);
        detailsFrame.add(backButton);

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        detailsFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        detailsFrame.setSize(800, 600);
        detailsFrame.setVisible(true);
        detailsFrame.setResizable(false);
        detailsFrame.setIconImage(appIcon);
        detailsFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            detailsFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == changePinButton){
                changePin();
            }
        if (e.getSource() == closeAccountButton) {
            if (Integer.parseInt(HomeScreen.activeLoan) > 0){
                JOptionPane.showMessageDialog(detailsFrame,"Cannot Close Account\nYou have Loan Pending");
            } else {
                closeAccount();
            }
        }
    }
    void changePin(){
        String confirmCurrentPin = JOptionPane.showInputDialog(detailsFrame,"Enter Current PIN");
        if (confirmCurrentPin.equals(HomeScreen.activePin)){
            String newPin = JOptionPane.showInputDialog(detailsFrame, "Enter new Pin");
            boolean pinOk = true;
            if (newPin.length() != 4 || !newPin.matches("\\d+")) {
                pinOk = false;
            }
            if (pinOk){
                try {
                    File file = new File("credentials/" + HomeScreen.activeGmail + ".txt");
                    BufferedReader reader = new BufferedReader(new FileReader(file));
                    StringBuilder content = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith(HomeScreen.activePin)) {
                            line = newPin;
                        }
                        content.append(line).append(System.lineSeparator());
                    }
                    reader.close();

                    BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                    writer.write(content.toString());
                    writer.close();

                    HomeScreen.activePin = newPin;
                    pinLabel.setText("Pin                               : " + newPin);
                    JOptionPane.showMessageDialog(detailsFrame,"Pin Changed Successfully");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(detailsFrame,"No Numbers or Symbols Allowed. Pin length should be 4");
            }

        } else {
            JOptionPane.showMessageDialog(detailsFrame,"Wrong PIN");
        }
    }
    void closeAccount(){
        String confirmDeletion = JOptionPane.showInputDialog(detailsFrame,"Are you sure you want to close this account?\nType 4-Digit pin to confirm");
        if (confirmDeletion != null && confirmDeletion.equals(HomeScreen.activePin)) {
            try {
                File delFile = new File("credentials/" + HomeScreen.activeGmail + ".txt");
                if (delFile.exists()){
                    if (delFile.delete()){
                        JOptionPane.showMessageDialog(detailsFrame,"Account Closed");
                        detailsFrame.setVisible(false);
                        new LoginScreen();
                    } else {
                        JOptionPane.showMessageDialog(detailsFrame,"Account not Closed");
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(detailsFrame, "An error occurred while closing the account.");
            }
        } else {
            JOptionPane.showMessageDialog(detailsFrame, "Invalid pin. Account closure canceled.");
        }
    }
}