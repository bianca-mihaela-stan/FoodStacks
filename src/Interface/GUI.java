package Interface;
import Classes.*;
import Functionalities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI implements ActionListener {

    private static JTextField emailText;
    private static JTextField passwordText;
    private static JLabel emailLabel;
    private static JLabel passwordLabel;


    private static JButton registerButton;
    private static JButton registerAsOwnerButton;
    private static JButton logInButton;

    private static JLabel waringLabel;
    private static JPanel panel1;
    private static JPanel clientMainPage;
    private static JFrame frame1;

    public static void main(String[] args){
        GUI gui = new GUI();
        panel1 = new JPanel();
        frame1 = new JFrame();
        frame1.setSize(1000, 1000);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(panel1);

        panel1.setLayout(null);

        emailLabel = new JLabel("Email: ");
        emailLabel.setBounds(10, 20, 80, 25);
        panel1.add(emailLabel);
        emailText = new JTextField();
        emailText.setBounds(100, 20, 165, 25);
        panel1.add(emailText);

        passwordLabel = new JLabel("Password: ");
        passwordLabel.setBounds(10, 50, 80, 25);
        panel1.add(passwordLabel);
        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        panel1.add(passwordText);

        registerButton = new JButton("Register");
        registerButton.setBounds(10, 80, 140, 25);
        registerButton.setActionCommand("register");
        registerButton.addActionListener(gui);
        panel1.add(registerButton);

        logInButton = new JButton("Log in");
        logInButton.setBounds(160, 80, 200, 25);
        logInButton.setActionCommand("logIn");
        logInButton.addActionListener(gui);
        panel1.add(logInButton);

        registerAsOwnerButton = new JButton("Register as owner");
        registerAsOwnerButton.setBounds(10, 110, 200, 25);
        registerAsOwnerButton.setActionCommand("registerAsOwner");
        registerAsOwnerButton.addActionListener(gui);
        panel1.add(registerAsOwnerButton);



        waringLabel = new JLabel("");
        waringLabel.setBounds(10, 140, 600, 25);
        panel1.add(waringLabel);

        frame1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        String email = emailText.getText();
        String password = passwordText.getText();
        if(command.equals("register"))
        {
            register(email, password);
        }
        else if(command.equals("logIn"))
        {
            logIn(email, password);
        }
    }

    private void register(String email, String password)
    {
        int x = PlatformService.Register(email, password);
        if(x == 0)
        {
            waringLabel.setText("This email already has an account!");
        }
        else if (x==1)
        {
            waringLabel.setText("You need to type a valid email!");
        }
        else if (x==2)
        {
            waringLabel.setText("Password must be: at least 8 characters, 1 uppercase, 1 lowercase, 1 number");

        }
        else if ( x== 3)
        {
            frame1.remove(panel1);
        }
    }

    private void logIn(String email, String password)
    {
        int x = PlatformService.LogIn(email, password);
        if(x == 0)
        {
            waringLabel.setText("You are already logged in!");
        }
        else if (x==1)
        {
            if(PlatformService.getLoggedInUser() instanceof Owner)
            {
                setupOwnerFrame();
            }
            else if(PlatformService.getLoggedInUser() instanceof Client)
            {
                setupClientFrame();
            }
        }
        else if (x==2)
        {
            waringLabel.setText("Email or password is incorrect!");

        }

    }

    private void registerAsOwner(String email, String password)
    {
        int x = PlatformService.RegisterAsOwner(email, password);
        if(x == 0)
        {
            waringLabel.setText("This email already has an account!");
        }
        else if (x==1)
        {
            waringLabel.setText("You need to type a valid email!");
        }
        else if (x==2)
        {
            waringLabel.setText("Password must be: at least 8 characters, 1 uppercase, 1 lowercase, 1 number");

        }
        else if ( x== 3)
        {
            setupOwnerFrame();
        }
    }

    private void setupOwnerFrame()
    {
        frame1.remove(panel1);
        clientMainPage = new JPanel();
        frame1.add(clientMainPage);
    }

    private void setupClientFrame()
    {
        frame1.remove(panel1);
        clientMainPage = new JPanel();
        frame1.add(clientMainPage);
    }


}
