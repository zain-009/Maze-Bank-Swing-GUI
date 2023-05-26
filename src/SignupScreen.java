import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

class SignupScreen extends Button implements ActionListener {
    JFrame signupFrame;
    JTextField nameTextfield,cnicTextfield,gmailTextfield,pinTextfield;
    JLabel SignupScreenImage,detailLabel,nameLabel,cnicLabel,gmailLabel,pinLabel,genderLabel,occupationLabel;
    ButtonGroup genderGroup,accountTypeGroup;
    JRadioButton accountTypeStudent,accountTypeBusiness,genderMale,genderFemale;
    ImageIcon bankImage;
    Button signupButton,backButton;
    List<JTextField> textFields;
    SignupScreen(){
        signupFrame = new JFrame("Maze Bank");
        signupFrame.setLayout(null);

        SignupScreenImage = new JLabel();
        ImageIcon baImage = new ImageIcon("mazeBank.png");
        Image bImage = baImage.getImage();
        Image scaledBankImage = bImage.getScaledInstance(200,100,Image.SCALE_SMOOTH);
        bankImage = new ImageIcon(scaledBankImage);
        SignupScreenImage.setIcon(bankImage);
        SignupScreenImage.setVisible(true);

        accountTypeStudent = new JRadioButton("Student");
        accountTypeBusiness = new JRadioButton("Business");
        genderGroup = new ButtonGroup();
        genderGroup.add(accountTypeStudent);
        genderGroup.add(accountTypeBusiness);
        genderMale = new JRadioButton("Male");
        genderFemale = new JRadioButton("Female");
        accountTypeGroup = new ButtonGroup();
        accountTypeGroup.add(genderMale);
        accountTypeGroup.add(genderFemale);

        detailLabel = new JLabel("Enter your details");
        detailLabel.setFont(new Font("",Font.BOLD,18));
        nameLabel = new JLabel("Your  Name    : ");
        nameLabel.setFont(new Font("",Font.BOLD,16));
        cnicLabel = new JLabel("Your   CNIC    : ");
        cnicLabel.setFont(new Font("",Font.BOLD,16));
        gmailLabel = new JLabel("Your  Gmail    : ");
        gmailLabel.setFont(new Font("",Font.BOLD,16));
        pinLabel = new JLabel("4 - Digit  Pin   : ");
        pinLabel.setFont(new Font("",Font.BOLD,16));
        genderLabel = new JLabel("Your Gender  : ");
        genderLabel.setFont(new Font("",Font.BOLD,16));
        occupationLabel = new JLabel("Account Type : ");
        occupationLabel.setFont(new Font("",Font.BOLD,16));

        nameTextfield = new JTextField();
        nameTextfield.setFont(new Font("",Font.BOLD,16));
        cnicTextfield = new JTextField();
        cnicTextfield.setFont(new Font("",Font.BOLD,16));
        gmailTextfield = new JTextField();
        gmailTextfield.setFont(new Font("",Font.BOLD,16));
        pinTextfield = new JTextField();
        pinTextfield.setFont(new Font("",Font.BOLD,16));

        detailLabel.setBounds(30,130,180,25);
        nameLabel.setBounds(30,170,160,25);
        cnicLabel.setBounds(30,210,160,25);
        gmailLabel.setBounds(30,250,160,25);
        pinLabel.setBounds(30,290,160,25);
        genderLabel.setBounds(30,330,160,25);
        occupationLabel.setBounds(30,370,160,25);
        nameTextfield.setBounds(200,170,160,25);
        cnicTextfield.setBounds(200,210,160,25);
        gmailTextfield.setBounds(200,250,160,25);
        pinTextfield.setBounds(200,290,160,25);
        genderMale.setBounds(200,330,80,25);
        genderFemale.setBounds(280,330,120,25);
        accountTypeStudent.setBounds(200,370,80,25);
        accountTypeBusiness.setBounds(280,370,120,25);

        signupButton = new Button("Create Account");
        signupButton.addActionListener(this);

        backButton = new Button("Go Back");
        backButton.addActionListener(this);

        SignupScreenImage.setBounds(20,10,200,100);
        backButton.setBounds(650,500,100,30);
        signupButton.setBounds(325,430,150,30);

        signupFrame.add(backButton);
        signupFrame.add(signupButton);
        signupFrame.add(SignupScreenImage);
        signupFrame.add(nameLabel);
        signupFrame.add(cnicLabel);
        signupFrame.add(gmailLabel);
        signupFrame.add(pinLabel);
        signupFrame.add(detailLabel);
        signupFrame.add(genderLabel);
        signupFrame.add(occupationLabel);
        signupFrame.add(nameTextfield);
        signupFrame.add(cnicTextfield);
        signupFrame.add(gmailTextfield);
        signupFrame.add(pinTextfield);
        signupFrame.add(genderMale);
        signupFrame.add(genderFemale);
        signupFrame.add(accountTypeStudent);
        signupFrame.add(accountTypeBusiness);

        Image appIcon = Toolkit.getDefaultToolkit().getImage("bankIcon.png");
        signupFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        signupFrame.setSize(800,600);
        signupFrame.setVisible(true);
        signupFrame.setResizable(false);
        signupFrame.setIconImage(appIcon);
        signupFrame.setLocationRelativeTo(null);

        textFields = new ArrayList<>();
        textFields.add(gmailTextfield);
        textFields.add(pinTextfield);
        textFields.add(nameTextfield);
        textFields.add(cnicTextfield);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == backButton){
            signupFrame.setVisible(false);
            new MainScreen();
        }
        if(e.getSource() == signupButton){
            boolean allTextFieldsOk = true;
            boolean allRadioButtonsOk = true;
            boolean pinOk = true;
            for (JTextField textField : textFields) {
                if (textField.getText().isEmpty()) {
                    allTextFieldsOk = false;
                }
            }
            if (genderGroup.getSelection() == null){
                allRadioButtonsOk = false;
            } else if (accountTypeGroup.getSelection() == null) {
                allRadioButtonsOk = false;
            }
            if (pinTextfield.getText().length() != 4 || !pinTextfield.getText().matches("\\d+")){
                pinOk = false;
            }
            if(allTextFieldsOk && allRadioButtonsOk && pinOk){
                    signup();
            } else {
                JOptionPane.showMessageDialog(signupFrame,"Please Enter Correct details");
            }
        }
    }
    void signup(){
        try {
            String fileName = gmailTextfield.getText();
            String filePath = "credentials/" + fileName + ".txt";
            File file = new File(filePath);
            if (file.exists()) {
                JOptionPane.showMessageDialog(signupFrame, "Gmail already in use");
                return;
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(gmailTextfield.getText() + "\n");
            writer.write(pinTextfield.getText()   + "\n");
            writer.write(nameTextfield.getText()  + "\n");
            writer.write(cnicTextfield.getText()  + "\n");
            writer.write("100\n");
            writer.write("0\n");
            if(genderMale.isSelected()){
                writer.write("Male\n");
            } else if(genderFemale.isSelected()){
                writer.write("Female\n");
            }
            if(accountTypeStudent.isSelected()){
                writer.write("Student\n");
            } else if(accountTypeBusiness.isSelected()){
                writer.write("Business\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(signupFrame,"Account Created Successfully");
            signupFrame.setVisible(false);
            new MainScreen();
        } catch (Exception x){
            System.out.println("Error saving credentials");
        }
    }
}
