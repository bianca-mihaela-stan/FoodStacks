package Classes;


import Functionalities.OwnerService;
import Functionalities.PlatformService;

public class Main {

    public static void main(String[] args)
    {
//        Client myClient = new Client.Builder("stan@gmail.com", "1234").build();
//        System.out.println(myClient.toString());
//
        Owner owner1 = new Owner.Builder("owner@gmail.com", "6283").build();
        System.out.println(owner1.toString());
//
        Address address1=new Address.Builder(Country.Romania, "Moreni", "HCC", "25").build();
        System.out.println(address1.toString());
//
        Restaurant restaurant1 = new Restaurant.Builder(owner1, address1, "happy pub").build();
        System.out.println(restaurant1.toString());

        Menu menu1 = new Menu();

//        PlatformService platform = PlatformService.getInstance();
//        OwnerService owner = OwnerService.getInstance();
//        owner.Register();

//        owner.addRestaurant(restaurant1);
//        owner.addMenu(menu1, restaurant1);
//        System.out.println(owner.getMenus(restaurant1));
//
//        obj.addAddress(address1, AddressIdentifier.Home);
//        System.out.println(obj.getAddresses());

//        obj.addMenu(menu1, restaurant1);
//        System.out.println(obj.getMenus(restaurant1));

    }
}
