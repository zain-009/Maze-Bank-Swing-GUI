import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class HomeScreen extends Button implements ActionListener {
    JFrame homeFrame;
    public static JLabel homeScreenImage;
    JLabel usernameLabel,balanceLabel,serviceLabel;
    Button depositButton,withdrawButton,loanButton,detailsButton, logoutButton;
    public static JPanel spacer,spacer1;
    ImageIcon bankImage;
    public static String activeUsername="", activeGmail="", activeCnic="", activePin="", activeGender="", activeAccountType="", activeBalance="", activeLoan = "";

    HomeScreen() {
        homeFrame = new JFrame("Maze Bank");
        homeFrame.setLayout(null);
        //Getting User Data from File :)
        try {
            BufferedReader reader = new BufferedReader(new FileReader("credentials/" + LoginScreen.loginGmailText + ".txt"));
            String tempGmail;
            while ((tempGmail = reader.readLine()) != null){
                if (tempGmail.equals(LoginScreen.tempGmail)){
                    activeGmail = tempGmail;
                    activePin = reader.readLine();
                    activeUsername = reader.readLine();
                    activeCnic = reader.readLine();
                    activeBalance = reader.readLine();
                    activeLoan = reader.readLine();
                    activeGender = reader.readLine();
                    activeAccountType = reader.readLine();
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(homeFrame, "File not Found");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(homeFrame, "IO Exception");
        }

        balanceLabel = new JLabel("Balance  Rs " + activeBalance);
        balanceLabel.setFont(new Font("",Font.BOLD,16));
        usernameLabel = new JLabel(activeUsername);
        usernameLabel.setFont(new Font("",Font.BOLD,16));
        usernameLabel.setForeground(Color.white);
        serviceLabel = new JLabel("Choose a service");
        serviceLabel.setFont(new Font("",Font.BOLD,14));
        serviceLabel.setForeground(Color.white);

        spacer = new JPanel();
        spacer.setBounds(30,110,725,5);
        spacer.setBackground(new Color(237,58,61,255));
        spacer1 = new JPanel();
        spacer1.setBackground(new Color(237,58,61,255));
        spacer1.setLayout(null);

        homeScreenImage = new JLabel();
        ImageIcon baImage = new ImageIcon("mazeBank.png");
        Image bImage = baImage.getImage();
        Image scaledBankImage = bImage.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
        bankImage = new ImageIcon(scaledBankImage);
        homeScreenImage.setIcon(bankImage);
        homeScreenImage.setVisible(true);

        depositButton = new Button("Deposit");
        depositButton.addActionListener(this);
        withdrawButton = new Button("Withdraw");
        withdrawButton.addActionListener(this);
        detailsButton = new Button("Account Details");
        detailsButton.addActionListener(this);
        loanButton = new Button("Loan");
        loanButton.addActionListener(this);
        logoutButton = new Button("Log Out");
        logoutButton.addActionListener(this);

        homeScreenImage.setBounds(20, 10, 200, 100);
        balanceLabel.setBounds(580, 80, 200, 30);
        spacer1.setBounds(30,140,725,50);
        usernameLabel.setBounds(10,5,150,20);
        serviceLabel.setBounds(300,25,150,20);
        depositButton.setBounds(290,220,200,50);
        withdrawButton.setBounds(290,300,200,50);
        loanButton.setBounds(290,380,200,50);
        detailsButton.setBounds(290,460,200,50);
        logoutButton.setBounds(650,500,100,30);

        spacer1.add(usernameLabel);
        spacer1.add(serviceLabel);
        homeFrame.add(homeScreenImage);
        homeFrame.add(balanceLabel);
        homeFrame.add(spacer);
        homeFrame.add(spacer1);
        homeFrame.add(depositButton);
        homeFrame.add(withdrawButton);
        homeFrame.add(loanButton);
        homeFrame.add(detailsButton);
        homeFrame.add(logoutButton);

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        homeFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        homeFrame.setSize(800, 600);
        homeFrame.setVisible(true);
        homeFrame.setResizable(false);
        homeFrame.setIconImage(appIcon);
        homeFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == logoutButton){
            homeFrame.setVisible(false);
            new LoginScreen();
        }
        if (e.getSource() == depositButton){
            homeFrame.setVisible(false);
            new DepositScreen();
        }
        if(e.getSource() == withdrawButton){
            homeFrame.setVisible(false);
            new WithdrawScreen();
        }
        if (e.getSource() == detailsButton){
            homeFrame.setVisible(false);
            new DetailsScreen();
        }
        if (e.getSource() == loanButton){
            homeFrame.setVisible(false);
            new LoanScreen();
        }
    }
}
