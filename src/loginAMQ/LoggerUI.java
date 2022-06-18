package loginAMQ;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.jms.JMSException;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class LoggerUI extends JFrame{
    private JPanel panel1;
    private JButton logInButton;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private String username;
    private String password;
    private static int MIN_LENGHT = 3;
    public LoggerUI() {
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String temp_username = textField1.getText();
                String temp_password = String.valueOf(passwordField1.getPassword());
                if(temp_username.length()<MIN_LENGHT | temp_password.length()<MIN_LENGHT){
                    dialogBox("Username or Password is less than minimum!");

                } else{
                    username = temp_username;
                    password = temp_password;
                    System.out.println("User: " + username);
                    System.out.println("Password: " + password);
                    LoginManager loginManager = new LoginManager(username, password);
                    ServerProccesing serverProccesing = null;
                    try {
                        serverProccesing = new ServerProccesing();
                        serverProccesing.sendKey();
                        loginManager.setKey();
                        loginManager.sendData();
                        if(serverProccesing.processData()==0){
                            dialogBox("Correct Credentials! Logged In!");
                        }else {
                            dialogBox("Wrong Credentials");
                        }

                    } catch (JMSException | IllegalBlockSizeException | UnsupportedEncodingException |
                            NoSuchPaddingException | BadPaddingException | NoSuchAlgorithmException |
                            InvalidKeyException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void dialogBox(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void test() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
        }
        JFrame frame = new JFrame("LoggerUI");
        frame.setContentPane(new LoggerUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
