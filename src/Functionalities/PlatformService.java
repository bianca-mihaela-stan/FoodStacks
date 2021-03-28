package Functionalities;
import Classes.*;
import java.util.*;

import java.util.Hashtable;
import java.util.stream.Stream;

public class PlatformService {
    protected static PlatformService instance;
    protected static Map<String, Client> clientsByEmail = new Hashtable<String, Client>();
    protected static Map<String, Owner> ownersByEmail = new Hashtable<String, Owner>();
    protected static Set<Driver> drivers = new HashSet<Driver>();
    protected static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();


    protected static User loggedInUser= null;

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

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        PlatformService.loggedInUser = loggedInUser;
    }

    protected static Boolean validateEmail(String email)
    {
        return email.matches("^(.+)@(.+)$");
    }

    protected static Boolean validatePassword(String password)
    {
        return  password.matches(".*[A-Z]+.*") &&
                password.matches(".*[a-z]+.*") &&
                password.matches(".*[0-9]+.*");
    }

    public static int Register(String email, String password)
    {

        if(clientsByEmail.containsKey(email))
        {
            return 0; //"This email already has an account! Press :q to exit.";
        }

        if(validateEmail(email)==false)
        {
            return 1; //"You need to type a valid email! Press :q to exit.");
        }


        System.out.println("at least 8 characters\n" +
                "at least 1 uppercase\n" +
                "at least 1 lowercase\n" +
                "at least 1 number\n");
        System.out.println("Password: ");

        if(validatePassword(password)== false){
            return 2;
//            system.out.println("Password must have: \n" +
//            "at least 8 characters\n" +
//            "at least 1 uppercase\n" +
//            "at least 1 lowercase\n" +
//            "at least 1 number\n");
        }
        else
        {
            System.out.println("Successfully registered!");
            Client new_client = new Client.Builder(email, password).build();
            clientsByEmail.put(email, new_client);
            loggedInUser=new_client;
            return 3;//Successfully registered!
        }

    }

    public static  int RegisterAsOwner(String email, String password)
    {
        if(ownersByEmail.containsKey(email))
        {
            return 0; //("This email already has an account! Press :q to exit.");
        }

        if(!validateEmail(email))
        {
            return 1;//"You need to type a valid email! Press :q to exit.");
        }

        if(!validatePassword(password)){
            return 2;
            //
        }

        System.out.println("Successfully logged in!");
        Owner new_owner = new Owner.Builder(email, password).build();
        ownersByEmail.put(email, new_owner);
        loggedInUser=new_owner;

        return 3;
    }


    public static int LogIn(String email, String password)
    {
        if(loggedInUser!=null)
        {
            return 0;//"You are already logged in!");
        }
        else {

            if(clientsByEmail.get(email)!=null && clientsByEmail.get(email).getPassword()==password){
                loggedInUser=clientsByEmail.get(email);
                return 1;//suceessfully signed in
            }
            else
            {
                return 2; //("Email or password is incorrect!");
            }

        }

    }

    public static void LogOut()
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

    public static ArrayList<Restaurant> getClientRestaurants()
    {
        return restaurants;
    }

    public static int addRestaurantToFavourites(Restaurant restaurant)
    {
        if(loggedInUser!= null && loggedInUser instanceof Client)
        {
            HashMap<RestaurantType, List<Restaurant>> favourites = ((Client) loggedInUser).getFavourites();
            if(favourites.get(restaurant.getRestaurantType())!= null && favourites.get(restaurant.getRestaurantType()).contains(restaurant) )
            {
                return 0; //restaurant alredy marked
            }
            else
            {
                favourites.put(restaurant.getRestaurantType(), null);
                favourites.get(restaurant.getRestaurantType()).add(restaurant);
                ((Client) loggedInUser).setFavourites(favourites);
                return 1; // successfuly added
            }
        }
        else
        {
            return 2; // you are not a client
        }
    }

    public static List<Menu> getMenus(Restaurant restaurant)
    {
        return restaurant.getMenus();
    }



}
