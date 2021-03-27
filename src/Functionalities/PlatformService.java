package Functionalities;
import Classes.*;
import java.util.*;

import java.util.Hashtable;
import java.util.stream.Stream;

public class PlatformService {
    protected static PlatformService instance;
    protected Map<String, Client> clientsByEmail = new Hashtable<String, Client>();
    protected Map<String, Owner> ownersByEmail = new Hashtable<String, Owner>();
    protected Set<Driver> drivers = new HashSet<Driver>();
    protected Set<Restaurant> restaurants = new HashSet<Restaurant>();


    private User loggedInUser= null;

    protected Scanner scanner = new Scanner(System.in);

    protected PlatformService()
    {
        System.out.println("Welcome!");
    }

    public static PlatformService getInstance()
    {
        if(instance==null)
        {
            instance= new PlatformService();
        }
        return instance;
    }

    protected Boolean validateEmail(String email)
    {
        return email.matches("^(.+)@(.+)$");
    }

    protected Boolean validatePassword(String password)
    {
        return  password.matches(".*[A-Z]+.*") &&
                password.matches(".*[a-z]+.*") &&
                password.matches(".*[0-9]+.*");
    }

    public void Register()
    {
        String email="", password="";
        Boolean emailOk = false;
        System.out.println("---------LOG IN---------");

        while(emailOk==false)
        {
            System.out.println("Email: ");
            email = scanner.next();
            if(email == ":q")
            {
                return;
            }

            if(clientsByEmail.containsKey(email))
            {
                System.out.println("This email already has an account! Press :q to exit.");
                return;
            }

            if(validateEmail(email)==false)
            {
                System.out.println("You need to type a valid email! Press :q to exit.");
            }


            emailOk=!clientsByEmail.containsKey(email) && validateEmail(email);
        }

        Boolean passwordOk = false;
        while(passwordOk==false)
        {
            System.out.println("at least 8 characters\n" +
                    "at least 1 uppercase\n" +
                    "at least 1 lowercase\n" +
                    "at least 1 number\n");
            System.out.println("Password: ");
            password = scanner.next();

            if(password == ":q")
            {
                return;
            }

            if(validatePassword(password)){
                passwordOk=true;
            }
            else
            {
                System.out.println("Password must have: \n" +
                        "at least 8 characters\n" +
                        "at least 1 uppercase\n" +
                        "at least 1 lowercase\n" +
                        "at least 1 number\n");
            }
        }

        if(emailOk && passwordOk)
        {
            System.out.println("Successfully registered!");
            Client new_client = new Client.Builder(email, password).build();
            clientsByEmail.put(email, new_client);
            loggedInUser=new_client;
        }

    }


    public void LogIn()
    {
        if(loggedInUser!=null)
        {
            System.out.println("You are already logged in!");
        }
        else {
            String email="", password="";
            Boolean ok = false;
            System.out.println("---------LOG IN---------");
            while(ok==false)
            {
                System.out.println("Email: ");
                email = scanner.next();
                if(email == ":q")
                {
                    return;
                }

                System.out.println("Password: ");
                password = scanner.next();

                if(password == ":q")
                {
                    return;
                }

                if(clientsByEmail.get(email).getPassword()==password){
                    ok=true;
                    loggedInUser=clientsByEmail.get(email);
                }
                else
                {
                    System.out.println("Email or password is incorrect!");
                }

            }
        }

    }

    public void LogOut()
    {
        if(loggedInUser==null)
        {
            System.out.println("You're not logged in!");
        }
        else
        {
            loggedInUser=null;
            System.out.println("You have successfully logged out!");
        }

    }

    public void editAddress(AddressIdentifier addressIdentifier, Address address)
    {
        if(loggedInUser!=null && loggedInUser instanceof Client)
        {
            HashMap<AddressIdentifier, Address> addresses = ((Client) loggedInUser).getAddresses();
            if(addresses.containsKey(addressIdentifier))
            {
                addresses.put(addressIdentifier, address);
            }
            else
            {
                System.out.println("Address identifier does not exist!");
            }
        }
    }

    public void addAddress(Address address, AddressIdentifier addressIdentifier)
    {
        if(loggedInUser!=null && loggedInUser instanceof Client)
        {
            HashMap<AddressIdentifier, Address> addresses = ((Client) loggedInUser).getAddresses();
            if(addresses.containsKey(addressIdentifier))
            {
                System.out.println("The address identifier alredy has an address. Do you wish to modify it? y/n");
                if(scanner.next()=="y")
                {
                    editAddress(addressIdentifier, address);
                }
                else
                {
                    return;
                }
            }
            else
            {
                addresses.put(addressIdentifier, address);
                System.out.println("Address was successfully added!");
            }
        }
    }

    public void deleteAddress(Address addressIdentifier)
    {
        if(loggedInUser!=null && loggedInUser instanceof Client)
        {
            HashMap<AddressIdentifier, Address> addresses = ((Client) loggedInUser).getAddresses();
            addresses.remove(addressIdentifier);
            System.out.println("Address was successfully removed!");
        }
    }



    public HashMap<AddressIdentifier, Address> getAddresses()
    {
        if(loggedInUser!=null && loggedInUser instanceof Client)
        {
            return ((Client) loggedInUser).getAddresses();
        }
        return null;
    }



}
