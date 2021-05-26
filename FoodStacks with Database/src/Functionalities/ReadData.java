//package Functionalities;
//import Classes.*;
//import com.opencsv.CSVReader;
//import com.opencsv.CSVReaderBuilder;
//import com.opencsv.exceptions.CsvException;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class ReadData {
//
//    private static ReadData instance;
//
//    public static ReadData getInstance() {
//        if (instance == null) {
//            instance = new ReadData();
//        }
//        return instance;
//    }
//
//    public static County County_contains(String test) {
//
//        for (County c : County.values()) {
//            if (c.name().equals(test)) {
//                return c;
//            }
//        }
//
//        return null;
//    }
//
//    public static AddressIdentifier AddressIdentifier_contains(String test) {
//
//        for (AddressIdentifier c : AddressIdentifier.values()) {
//            if (c.name().equals(test)) {
//                return c;
//            }
//        }
//
//        return null;
//    }
//
//    public void readData() throws FileNotFoundException {
//
//        try {
//
//            CSVReader reader1 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Addresses.csv")).withSkipLines(1).build();
//
//            CSVReader reader2 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Carts_Dishes.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader3 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Categories_Menus.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader4 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Clients.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader5 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Clients_Addresses.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader6 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Dishes.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader7 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Favourites.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader8 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Ingredients.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader9 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Menus.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader10 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Orders.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader11 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Owners.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader12 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Recipes.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader13 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Restaurants.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader14 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Restaurants_Addresses.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader15 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Reviews.csv")).withSkipLines(1)
//                    .build();
//
//            CSVReader reader16 = new CSVReaderBuilder(new FileReader("F:\\Github\\FoodStacks\\Data\\Users.csv")).withSkipLines(1)
//                    .build();
//
//
//            List<String[]> addresses = reader1.readAll();
//            List<String[]> carts_dishes = reader2.readAll();
//            List<String[]> categories_menus = reader3.readAll();
//            List<String[]> clients = reader4.readAll();
//            List<String[]> clients_addresses = reader5.readAll();
//            List<String[]> dishes = reader6.readAll();
//            List<String[]> favourites = reader7.readAll();
//            List<String[]> ingredients = reader8.readAll();
//            List<String[]> menus = reader9.readAll();
//            List<String[]> orders = reader10.readAll();
//            List<String[]> owners = reader11.readAll();
//            List<String[]> recipes = reader12.readAll();
//            List<String[]> restaurants = reader13.readAll();
//            List<String[]> restaurants_addresses = reader14.readAll();
//            List<String[]> reviews = reader15.readAll();
//            List<String[]> users = reader16.readAll();
//
//            List<Address> addressList = new ArrayList<>();
//
//            int i = 0;
//            for (String[] linie : addresses)
//            {
//                //City,County,Sector,Street,Number,Block,Entrance,Floor,ApartmentNumber,id
//                String city = addresses.get(i)[0].trim();
//                String scounty = addresses.get(i)[1].trim();
//                County county = null;
//                if (County_contains(scounty) != null)
//                    county = County_contains(scounty);
//                Integer sector = null;
//                if (addresses.get(i)[2].trim()!="")
//                    sector = Integer.parseInt(addresses.get(i)[2].trim());
//                String street = addresses.get(i)[3];
//                String number = addresses.get(i)[4];
//                String block = addresses.get(i)[5];
//                String entrance = addresses.get(i)[6];
//                Integer floor = null;
//                if (addresses.get(i)[7].trim()!="")
//                    floor = Integer.parseInt(addresses.get(i)[7].trim());
//                Integer apartmentNumber = null;
//                if (addresses.get(i)[7].trim()!="")
//                    apartmentNumber = Integer.parseInt(addresses.get(i)[8]);
//                Integer id = null;
//                if (addresses.get(i)[9]!="")
//                        id = Integer.parseInt(addresses.get(i)[9]);
//                addressList.add(new Address(county,city,sector,street,number,block,entrance,floor,apartmentNumber));
//                i++;
//            }
//
//            List<User> userList = new ArrayList<>();
//
//            i = 0;
//            for (String[] linie : users)
//            {
//                //id,Name,Surname,Username,Email,password,phoneNumber
//                Integer id = Integer.parseInt(users.get(i)[0]);
//                String name = users.get(i)[1].trim();
//                String surname = users.get(i)[2].trim();
//                String username = users.get(i)[3].trim();
//                String email = users.get(i)[4].trim();
//                String password = users.get(i)[5].trim();
//                String phoneNumber = users.get(i)[6].trim();
//                userList.add(new User(name, surname,username, email, password, phoneNumber));
//                i++;
//            }
//
//            List<Client> clientList = new ArrayList<>();
//            i = 0;
//            for (String[] linie : clients)
//            {
//                //id,Name,Surname,Username,Email,password,phoneNumber
//                Integer client_id = Integer.parseInt(clients_addresses.get(i)[0]);
//                Integer user_id = Integer.parseInt(clients_addresses.get(i)[1]);
//
//                Client client =  new Client(userList.get(user_id-1));
//                clientList.add(client);
//                i++;
//            }
//
//
//            i = 0;
//            for (String[] linie : clients_addresses)
//            {
//                //id,Name,Surname,Username,Email,password,phoneNumber
//                Integer client_id = Integer.parseInt(clients_addresses.get(i)[0]);
//                Integer address_id = Integer.parseInt(clients_addresses.get(i)[1]);
//                String saddress = clients_addresses.get(i)[2];
//                AddressIdentifier address_identifier = null;
//                if (AddressIdentifier_contains(saddress) != null)
//                    address_identifier = AddressIdentifier_contains(saddress);
//
//                clientList.get(client_id-1).addAddress(addressList.get(address_id-1), address_identifier);
//                i++;
//            }
//
//        }
//        catch (IOException | CsvException e) {
//            //e.printStackTrace();
//            System.out.println(e.getMessage());
//        }
////
////        return localuri;
//
//    }
//}
