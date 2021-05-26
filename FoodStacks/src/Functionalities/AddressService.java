package Functionalities;

import Classes.Address;
import Classes.Audit;
import Classes.County;
import Functionalities.PlatformService;

import static java.lang.Integer.parseInt;

public class AddressService extends PlatformService {

    private static AddressService instance;
    private static Audit audit;

    private AddressService()
    {
        audit = Audit.getInstance("F:\\Github\\FoodStacks\\Data\\Audit.csv");
    }


    public static AddressService getInstance()
    {
        if(instance==null)
        {
            instance = new AddressService();
        }
        return instance;
    } 
    
    public static Address addressFromInput()
    {
        audit.writeToFile();
        System.out.println("Pick one county: \n");
        County[] counties = County.values();
        for(int i=0; i<counties.length; i++)
        {
            System.out.println(i + ": " + counties[i] + "\n");
        }
        System.out.println("Pick county (insert its number): ");
        String whichCounty = scanner.next();
        County county = counties[parseInt(whichCounty)];
        System.out.println("Insert city: ");
        String city = scanner.next();
        System.out.println("Insert street: ");
        String street = scanner.next();
        System.out.println("Insert street number: ");
        String number = scanner.next();
        System.out.println("Insert block: (insert :s to skip) ");
        String block = scanner.next();
        if(block == ":s")
        {
            block = null;
        }
        System.out.println("Insert entrance: (insert :s to skip) ");
        String entrance = scanner.next();
        if(entrance == ":s")
        {
            entrance = null;
        }

        System.out.println("Insert floor: (insert :s to skip) ");
        var floor = scanner.next();
        if(floor == ":s")
        {
            floor = null;
        }
        Integer floorNumber = Integer.valueOf(floor);

        System.out.println("Insert apartment number: (insert :s to skip) ");
        String apartment= scanner.next();
        if(apartment == ":s")
        {
            apartment = null;
        }
        Integer apartmentNumber = Integer.valueOf(apartment);

        return new Address.Builder(county, city, street, number).withBlock(block).withEntrance(entrance).withFloor(floorNumber).withApartmentNumber(apartmentNumber).build();
    }

}
