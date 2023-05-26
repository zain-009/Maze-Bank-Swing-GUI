import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class MainScreen extends Button implements ActionListener {
    JFrame loginFrame;
    JLabel loginScreenImage;
    Button loginButton,signupButton,exitButton;
    ImageIcon bankImage;
    MainScreen(){
        loginFrame = new JFrame("Maze Bank");
        loginFrame.setLayout(null);

        loginScreenImage = new JLabel();
        ImageIcon baImage = new ImageIcon("mazeBank.png");
        Image bImage = baImage.getImage();
        loginScreenImage.setBounds(200,80,400,200);
        Image scaledBankImage = bImage.getScaledInstance(400,200,Image.SCALE_SMOOTH);
        bankImage = new ImageIcon(scaledBankImage);
        loginScreenImage.setIcon(bankImage);
        loginScreenImage.setVisible(true);

        loginButton = new Button("Login");
        loginButton.addActionListener(this);

        signupButton = new Button("Signup");
        signupButton.addActionListener(this);

        exitButton = new Button("Exit");
        exitButton.addActionListener(this);

        loginButton.setBounds(325,325,150,30);
        signupButton.setBounds(325,400,150,30);
        exitButton.setBounds(650,500,100,30);


        loginFrame.add(loginScreenImage);
        loginFrame.add(loginButton);
        loginFrame.add(signupButton);
        loginFrame.add(exitButton);

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
        if(e.getSource() == loginButton){
            loginFrame.setVisible(false);
            new LoginScreen();
        }
        if(e.getSource() == signupButton){
            loginFrame.setVisible(false);
            new SignupScreen();
        }
        if(e.getSource() == exitButton){
            System.exit(0);
        }
    }
}
