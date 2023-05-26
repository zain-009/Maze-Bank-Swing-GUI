import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class LoginScreen extends Button implements ActionListener {
    public static String tempGmail;
    JFrame loginFrame;
    JTextField loginGmailTextfield;
    JPasswordField loginPinTextfield;
    JLabel loginScreenImage,gmailText,pinText;
    ImageIcon bankImage;
    Button loginButton,backButton;
    public static String loginGmailText;

    LoginScreen(){
        loginFrame = new JFrame("Maze Bank");
        loginFrame.setLayout(null);

        loginScreenImage = new JLabel();
        ImageIcon baImage = new ImageIcon("mazeBank.png");
        Image bImage = baImage.getImage();
        loginScreenImage.setBounds(200,30,400,200);
        Image scaledBankImage = bImage.getScaledInstance(400,200,Image.SCALE_SMOOTH);
        bankImage = new ImageIcon(scaledBankImage);
        loginScreenImage.setIcon(bankImage);
        loginScreenImage.setVisible(true);

        gmailText = new JLabel("Enter your gmail");
        gmailText.setBounds(320,240,160,25);
        pinText = new JLabel("Enter your pin");
        pinText.setBounds(320,315,160,25);

        loginGmailTextfield = new JTextField();
        loginGmailTextfield.setBounds(320,265,160,25);
        loginGmailTextfield.setFont(new Font("",Font.BOLD,12));

        loginPinTextfield = new JPasswordField();
        loginPinTextfield.setBounds(320,340,160,25);

        loginButton = new Button("Login");
        loginButton.addActionListener(this);

        backButton = new Button("Go Back");
        backButton.addActionListener(this);


        backButton.setBounds(650,500,100,30);
        loginButton.setBounds(325,400,150,30);

        loginFrame.add(loginScreenImage);
        loginFrame.add(backButton);
        loginFrame.add(gmailText);
        loginFrame.add(loginGmailTextfield);
        loginFrame.add(loginPinTextfield);
        loginFrame.add(loginButton);
        loginFrame.add(pinText);

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        loginFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        loginFrame.setSize(800,600);
        loginFrame.setVisible(true);
        loginFrame.setResizable(false);
        loginFrame.setIconImage(appIcon);
        loginFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton){
            String loginGmail = loginGmailTextfield.getText(),loginPin = loginPinTextfield.getText();
            boolean loginSuccessful = false;
            try {
                BufferedReader reader = new BufferedReader(new FileReader("credentials/" + loginGmailTextfield.getText() + ".txt"));
                while ((tempGmail = reader.readLine()) != null){
                    if (tempGmail.equals(loginGmail)){
                        tempGmail = loginGmail;
                        String tempPin = reader.readLine();
                        if (tempPin.equals(loginPin)){
                            loginSuccessful = true;
                            break;
                        }
                    }
                }
                reader.close();
                if (loginSuccessful){
                    JOptionPane.showMessageDialog(loginFrame,"Login Successful");
                    loginGmailText = loginGmailTextfield.getText();
                    loginFrame.setVisible(false);
                    new HomeScreen();
                } else {
                    JOptionPane.showMessageDialog(loginFrame,"Wrong Credentials");
                }
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(loginFrame,"Wrong Gmail and Password");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(loginFrame,"Error reading credentials.txt");
            }
        }
        if(e.getSource() == backButton){
            loginFrame.setVisible(false);
            loginGmailTextfield.setText("");
            loginPinTextfield.setText("");
            new MainScreen();
        }
    }
}
