package Interface;
import Classes.*;
import Functionalities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPanel extends JPanel{
    public RegisterPanel()
    {
        JLabel emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10, 20, 80, 25);

        JTextField emailText = new JTextField();
        emailText.setBounds(100, 20, 165, 25);


        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 50, 80, 25);

        JTextField passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(10, 80, 140, 25);
        registerButton.setActionCommand("register");

        JButton logInButton = new JButton("Log in");
        logInButton.setBounds(160, 80, 200, 25);
        logInButton.setActionCommand("logIn");



        JButton registerAsOwnerButton = new JButton("Register as owner");
        registerAsOwnerButton.setBounds(10, 110, 200, 25);
        registerAsOwnerButton.setActionCommand("registerAsOwner");

    }
}
