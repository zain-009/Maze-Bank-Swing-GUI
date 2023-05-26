import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class SplashScreen {
    static JFrame splashFrame;
    static ImageIcon bankImage;
    static JLabel loginScreenImage;
    static JProgressBar progressBar;
    public static void main(String[] args) {
        splashFrame = new JFrame("Maze Bank");
        splashFrame.setLayout(null);

        loginScreenImage = new JLabel();
        Image bImage = new ImageIcon("mazeBank.png").getImage();
        loginScreenImage.setBounds(200,80,400,220);
        Image scaledBankImage = bImage.getScaledInstance(400,220,Image.SCALE_AREA_AVERAGING);
        bankImage = new ImageIcon(scaledBankImage);
        loginScreenImage.setIcon(bankImage);
        loginScreenImage.setVisible(true);

        progressBar = new JProgressBar(0,100);
        splashFrame.add(loginScreenImage);
        splashFrame.add(progressBar);

        progressBar.setBounds(250,400,300,40);
        progressBar.setBorderPainted(false);
        progressBar.setValue(0);
        progressBar.setForeground(new Color(237,58,61,255));

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        splashFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        splashFrame.setSize(800,600);
        splashFrame.setVisible(true);
        splashFrame.setResizable(false);
        splashFrame.setIconImage(appIcon);
        splashFrame.setLocationRelativeTo(null);
        updateProgressBar();
        splashFrame.setVisible(false);
        new MainScreen();
    }
    static void updateProgressBar(){
        try {
            for (int i=0;i<=100;i++){
                Thread.sleep(20);
                progressBar.setValue(i);
            }
        } catch (InterruptedException e) {
            JOptionPane.showMessageDialog(splashFrame,"Progress Bar  not progressing");
            throw new RuntimeException(e);
        }
    }
}
