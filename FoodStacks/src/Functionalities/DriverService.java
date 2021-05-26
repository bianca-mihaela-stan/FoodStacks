package Functionalities;

import Classes.Audit;
import Classes.Client;
import Classes.Driver;

import java.time.LocalDate;

public class DriverService extends PlatformService{

    private static DriverService instance;
    Audit audit;

    private DriverService()
    {
        audit = Audit.getInstance("F:\\Github\\FoodStacks\\Data\\Audit.csv");
    }

    public static DriverService getInstance()
    {
        if(instance==null)
        {
            instance = new DriverService();
        }
        return instance;
    }


    public void Register(String email, String password)
    {
        audit.writeToFile();
        if(Client.getClientsByEmail().containsKey(email))
        {
            System.out.println("This email already has an account!");
            return;
        }

        if(!validateEmail(email))
        {
            System.out.println("You need to type a valid email!");
            return;
        }


        if(!validatePassword(password)){
            System.out.println("You need to type a valid password (1 uppercase, 1 lowercase, 1 number)");
        }
        else
        {
            System.out.println("Successfully registered!");
            loggedInUser = new Driver.Builder(email, password).build();
        }

    }

    public void showDeliveriesToday()
    {
        audit.writeToFile();
        if(((Driver)loggedInUser).getDeliveries().containsKey(LocalDate.now()))
            System.out.println(((Driver)loggedInUser).getDeliveries().get(LocalDate.now()));
    }

}
