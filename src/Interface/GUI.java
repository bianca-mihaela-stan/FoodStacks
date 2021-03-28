package Interface;
import Classes.*;
import Functionalities.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class GUI implements ActionListener {

    private static JTextField emailText;
    private static JTextField passwordText;
    private static JLabel emailLabel;
    private static JLabel passwordLabel;


    private static JButton registerButton;
    private static JButton registerAsOwnerButton;
    private static JButton logInButton;
    private static JButton logOutButton;
    private static JButton clientAccountButton;
    private static JButton clientOrders;
    private static JButton favourites;
    private static JButton cart;
    private static ArrayList<JButton> clientRestaurantsButtons;

    private static JLabel waringLabel;
    private static JPanel panel1;
    private static JPanel panel2;
    private static JPanel clientMainPage;
    private static JFrame frame1;
    private static JFrame frame2;
    public static GUI gui = new GUI();

    public static void main(String[] args){


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
            frame1.setVisible(false);
            setupClientFrame();
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
                frame1.setVisible(false);
                setupOwnerFrame();
            }
            else if(PlatformService.getLoggedInUser() instanceof Client)
            {
                frame1.setVisible(false);
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
            frame1.setVisible(false);
            setupOwnerFrame();
        }
    }

    private static void setupOwnerFrame()
    {
        frame1.remove(panel1);
        clientMainPage = new JPanel();
        frame1.add(clientMainPage);
    }

    private static void setupClientFrame()
    {
        panel2 = new JPanel();
        frame2 = new JFrame();
        frame2.setSize(1000, 1000);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame2.add(panel2);

        logOutButton = new JButton("Log out");
        logOutButton.setBounds(900, 50, 100, 25);
        logOutButton.setActionCommand("logOut");
        logOutButton.addActionListener(gui);
        panel2.add(logOutButton);

        clientAccountButton= new JButton("Account");
        clientAccountButton.setBounds(900, 80, 100, 25);
        clientAccountButton.setActionCommand("clientAccount");
        clientAccountButton.addActionListener(gui);
        panel2.add(clientAccountButton);

        clientOrders= new JButton("Orders");
        clientOrders.setBounds(900, 110, 100, 25);
        clientOrders.setActionCommand("clientOrders");
        clientOrders.addActionListener(gui);
        panel2.add(clientOrders);

        favourites= new JButton("Favourites");
        favourites.setBounds(900, 140, 100, 25);
        favourites.setActionCommand("favourites");
        favourites.addActionListener(gui);
        panel2.add(favourites);

        cart= new JButton("Cart");
        cart.setBounds(900, 170, 100, 25);
        cart.setActionCommand("favourites");
        cart.addActionListener(gui);
        panel2.add(cart);

        Set<Restaurant> restaurants = PlatformService.getClientRestaurants();
        int j=0;

        for( Restaurant i : restaurants)
        {
            JButton button = new JButton(i.getName());
            button.setBounds(10, 200, 100, 25);
            button.setActionCommand();

            clientRestaurantsButtons.add(new JButton(i.getName()));
            clientRestaurantsButtons[j]
        }

        frame2.setVisible(true);

    }


}
