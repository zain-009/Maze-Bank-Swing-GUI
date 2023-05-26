import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class DepositScreen extends Button implements ActionListener {
    JFrame depositFrame;
    Button cancelButton,depositButton;
    JLabel depositAmountLabel;
    JTextField depositAmountTextfield;

    DepositScreen(){
        depositFrame = new JFrame("Maze Bank");
        depositFrame.setLayout(null);

        depositAmountLabel = new JLabel("Enter amount to deposit");
        depositAmountLabel.setFont(new Font("",Font.BOLD,16));

        depositAmountTextfield = new JTextField();
        depositAmountTextfield.setFont(new Font("",Font.BOLD,18));

        depositButton = new Button("Deposit");
        depositButton.addActionListener(this);
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);

        depositAmountLabel.setBounds(300,250,200,30);
        depositAmountTextfield.setBounds(315,290,150,30);
        depositButton.setBounds(315,350,150,50);
        cancelButton.setBounds(650,500,100,30);

        depositFrame.add(HomeScreen.spacer);
        depositFrame.add(HomeScreen.spacer1);
        depositFrame.add(HomeScreen.homeScreenImage);
        depositFrame.add(depositAmountLabel);
        depositFrame.add(depositAmountTextfield);
        depositFrame.add(depositButton);
        depositFrame.add(cancelButton);

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        depositFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        depositFrame.setSize(800, 600);
        depositFrame.setVisible(true);
        depositFrame.setResizable(false);
        depositFrame.setIconImage(appIcon);
        depositFrame.setLocationRelativeTo(null);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancelButton){
            depositFrame.setVisible(false);
            new HomeScreen();
        }
        if (e.getSource() == depositButton){
            try {
               int depositAmount = Integer.parseInt(depositAmountTextfield.getText());
               if (depositAmount >= 500){
                   deposit(depositAmount);
                   depositFrame.setVisible(false);
                   new HomeScreen();
               } else {
                JOptionPane.showMessageDialog(depositFrame,"Minimum Deposit Amount : 500");
                depositAmountTextfield.setText("");
               }
            } catch (NumberFormatException x){
                JOptionPane.showMessageDialog(depositFrame,"Please Enter Amount in Numbers");
            }
        }
    }
    void deposit(int depositAmount){
        try {
            File file = new File("credentials/" + HomeScreen.activeGmail + ".txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(HomeScreen.activeBalance)) {
                    line = String.valueOf(Integer.parseInt(HomeScreen.activeBalance) + depositAmount);
                }
                content.append(line).append(System.lineSeparator());
            }
            reader.close();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();
            JOptionPane.showMessageDialog(depositFrame,"Rs"+depositAmount+" Deposited Successfully");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
