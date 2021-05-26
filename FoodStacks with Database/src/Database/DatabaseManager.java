package Database;

import Classes.*;
import Classes.Driver;
import org.javatuples.Pair;
import org.javatuples.Triplet;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.lang.Math.max;

public class DatabaseManager {

    public void insertAddress(Address address)
    {
        String insertAddressSql = "INSERT INTO address VALUES(null, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getHouse_number());
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public List<Address> getAllAddresses()
    {
        String selectSql = "SELECT * FROM address";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Address> addresses = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("address_id");
                var street = resultSet.getString("street");
                var house_number = resultSet.getString("house_number");

                Address address = new Address(id, street, house_number);
                addresses.add(address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return addresses;
    }

    public void insertDish(Dish dish)
    {
        String insertDishSql1 = "INSERT INTO dish VALUES(null, ?)";
        String insertDishSql2 = "INSERT INTO recipe VALUES(?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(insertDishSql1);
            preparedStatement1.setString(1, dish.getName());

            preparedStatement1.execute();
            Dish.incrementID();
        } catch (SQLException e)    {
            e.printStackTrace();
        }

        for(var elem : dish.getRecipe())
        {
            try {
                var ingredient = elem.getValue0();
                var quantity = elem.getValue1();
                var unit_of_measurement = elem.getValue2();
                PreparedStatement preparedStatement2 = connection.prepareStatement(insertDishSql2);
                preparedStatement2.setLong(1, Dish.getID());
                preparedStatement2.setString(2, ingredient);
                preparedStatement2.setDouble(3, quantity);
                preparedStatement2.setString(4, unit_of_measurement);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

        }
    }

    public List<Dish> getAllDishes()
    {
        String selectSql = "SELECT * FROM dish";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Dish> dishes = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("dish_id");
                var name = resultSet.getString("dish_name");
                Dish.setID(max(Dish.getID(), id)+1);

                String selectSql2 = "SELECT * FROM recipe where dish_id = ?";
                List<Triplet<String, Double, String>> elements = new ArrayList<>();

                PreparedStatement preparedStatement = connection.prepareStatement(selectSql2);
                preparedStatement.setLong(1, id);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while(resultSet2.next())
                {
                    var ingredient = resultSet2.getString("ingredient");
                    var quantity = resultSet2.getDouble("quantity");
                    var unit_of_measurement = resultSet2.getString("unit_of_measurement");

                    elements.add(new Triplet<>(ingredient, quantity, unit_of_measurement));
                }

                Dish dish = new Dish.Builder(name).withId(id).withRecipe(elements).build();

                dishes.add(dish);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dishes;
    }

    public List<Menu> getAllMenus()
    {
        String selectSql = "SELECT * FROM menu";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Menu> menus = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("menu_id");
                var name = resultSet.getString("menu_name");
                var restaurant_id = resultSet.getLong("restaurant_id");
                Menu.setID(max(Dish.getID(), id)+1);

                String selectSql2 = "SELECT * FROM menus_dishes where menu_id = ?";
                List<Pair<Dish, Double>> elements = new ArrayList<>();

                PreparedStatement preparedStatement = connection.prepareStatement(selectSql2);
                preparedStatement.setLong(1, id);
                ResultSet resultSet2 = preparedStatement.executeQuery();

                while(resultSet2.next())
                {
                    var dish_id = resultSet2.getLong("dish_id");
                    var price = resultSet2.getDouble("price");

                    elements.add(new Pair<>(getDish(dish_id), price));
                }

                Menu menu = new Menu.Builder(name).withElements(elements).build();

                menus.add(menu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return menus;
    }

    public List<Order> getAllOrders()
    {
        String selectSql = "SELECT * FROM order_table";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("order_id");
                var time_ordered = resultSet.getDate("time_ordered");
                var time_delivered = resultSet.getDate("time_delivered");
                var address_id = resultSet.getLong("address_id");
                var final_price = resultSet.getDouble("final_price");
                var user_id = resultSet.getLong("user_id");
                var restaurant_id = resultSet.getLong("restaurant_id");
                var driver_id = resultSet.getLong("driver_id");

                Order.setID(max(Order.getID(), id)+1);

                try {
                    var user = getClient(user_id);
                    var restaurant = getRestaurant(restaurant_id);


                    String selectSql2 = "SELECT * FROM orders_dishes where order_id = ?";

                    List<Triplet<Dish, Integer, Double>> dishes = new ArrayList<>();

                    PreparedStatement preparedStatement = connection.prepareStatement(selectSql2);
                    preparedStatement.setLong(1, id);
                    ResultSet resultSet2 = preparedStatement.executeQuery();

                    while (resultSet2.next()) {
                        var dish_id = resultSet2.getLong("dish_id");
                        var price = resultSet2.getDouble("price");
                        var quantity = resultSet2.getInt("quantity");

                        dishes.add(new Triplet<Dish, Integer, Double>(getDish(dish_id), quantity, price));
                    }

                    Order order = new Order.Builder(restaurant, user).withTimeOrdered(time_ordered).withDishes(dishes).build();
//                Order order = new Order();
                    orders.add(order);
                }
                catch(IndexOutOfBoundsException exception)
                {
                    System.out.println("Invalid foreig key at getAllOrders.");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return orders;
    }

    public void insertOrder(Order order)
    {
        String insertAddressSql2 = "INSERT INTO orders_dishes VALUES(?, ?, ?, ?)";
        String insertAddressSql1 = "INSERT INTO order_table VALUES(null, ?, null, null, ?, ?, ?, null)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql1);
//            preparedStatement.setLong(1, order.getId());
            preparedStatement.setDate(1, (Date) order.getTimeOrdered());
            preparedStatement.setDouble(2, order.getFinalPrice());
            preparedStatement.setLong(3, order.getClient().getId());
            preparedStatement.setLong(4, order.getRestaurant().getId());
            preparedStatement.execute();

            for(var x : order.getDishesOrdered())
            {
                PreparedStatement preparedStatement2 = connection.prepareStatement(insertAddressSql2);
                preparedStatement2.setLong(1, order.getId());
                preparedStatement2.setLong(2, x.getValue0().getId());
                preparedStatement2.setDouble(3, x.getValue1());
                preparedStatement2.setDouble(4, x.getValue2());
                preparedStatement2.execute();
            }
            Order.setID(Order.getID()+1);

        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void insertMenu(Menu menu)
    {
        String insertAddressSql = "INSERT INTO menu VALUES(null, ?, ?)";
        String insertAddressSql2 = "INSERT INTO menus_dishes VALUES(?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, menu.getName());
            preparedStatement.setLong(2, menu.getRestaurant().getId());
            preparedStatement.execute();

            for(var x : menu.getElements())
            {
                PreparedStatement preparedStatement2 = connection.prepareStatement(insertAddressSql2);
                preparedStatement2.setLong(1, menu.getId());
                preparedStatement2.setLong(2, x.getValue0().getId());
                preparedStatement2.setDouble(3, x.getValue1());
                preparedStatement2.execute();
            }
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public Dish getDish(Long dish_id) throws IndexOutOfBoundsException
    {
        Predicate<Dish> c1 = p -> p.getId() == dish_id;
        var dish = getAllDishes().stream().filter(c1).toList().get(0);
        return dish;
    }

    public Menu getMenu(Long menu_id) throws IndexOutOfBoundsException
    {
        Predicate<Menu> c1 = p -> p.getId() == menu_id;
        var menu = getAllMenus().stream().filter(c1).toList().get(0);
        return menu;
    }

    public Address getAddress(Long address_id) throws IndexOutOfBoundsException
    {
        Predicate<Address> c1 = p -> p.getId() == address_id;
        var address = getAllAddresses().stream().filter(c1).toList().get(0);
        return address;
    }

    public Client getClient(Long client_id) throws IndexOutOfBoundsException
    {
        Predicate<Client> c1 = p -> p.getId().equals(client_id);
        var client = getAllClients().stream().filter(c1).toList().get(0);
        return client;
    }

    public Owner getOwner(Long owner_id) throws IndexOutOfBoundsException
    {
        Predicate<Owner> c1 = p -> p.getId() == owner_id;
        var owner = getAllOwners().stream().filter(c1).toList().get(0);
        return owner;
    }

    public Restaurant getRestaurant(Long restaurant_id) throws IndexOutOfBoundsException
    {
        Predicate<Restaurant> c1 = p -> p.getId() == restaurant_id;
        var restaurant = getAllRestaurants().stream().filter(c1).toList().get(0);
        return restaurant;
    }

    public void insertRestaurant(Restaurant restaurant)
    {
        String insertAddressSql = "INSERT INTO restaurant VALUES(null, ?, ?, ?, ?)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertAddressSql);
            preparedStatement.setString(1, restaurant.getName());
            preparedStatement.setLong(2, restaurant.getAddress().getId());
            preparedStatement.setLong(3, restaurant.getOwner().getId());
            preparedStatement.setString(4, restaurant.getRestaurantType().toString());
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public List<Restaurant> getAllRestaurants()
    {
        String selectSql = "SELECT * FROM restaurant";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Restaurant> restaurants = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("restaurant_id");
                var name = resultSet.getString("restaurant_name");
                var address_id = resultSet.getLong("address_id");
                var owner_id = resultSet.getLong("owner_id");
                var restaurant_type = resultSet.getString("restaurant_type");
                RestaurantType restaurant_actual_type = RestaurantType.valueOf(restaurant_type);

                try {
                    var address = getAddress(address_id);

                    var owner = getOwner(owner_id);

                    Restaurant restaurant = new Restaurant.Builder(owner, address, name).withId(id).withRestaurantType(restaurant_actual_type).build();
                    restaurants.add(restaurant);
                }
                catch (IndexOutOfBoundsException exception)
                {
                    System.out.println("Invalid foreign key at getAllRestaurants.");
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return restaurants;
    }

    public void insertClient(Client user)
    {
        String insertClientSql = "INSERT INTO user VALUES(?, ?, ?, ?, ?, ?, null, null)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertClientSql);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setInt(6, 1);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }

    public void insertOwner(Owner owner)
    {
        String insertClientSql = "INSERT INTO user VALUES(null, ?, ?, ?, ?, ?, null, null)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertClientSql);
//            preparedStatement.setLong(1, owner.getId());
            preparedStatement.setString(1, owner.getName());
            preparedStatement.setString(2, owner.getEmail());
            preparedStatement.setString(3, owner.getPassword());
            preparedStatement.setString(4, owner.getPhoneNumber());
            preparedStatement.setInt(5, 2);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }


    public void insertDriver(Driver driver)
    {
        String insertClientSql = "INSERT INTO user VALUES(null, ?, ?, ?, ?, ?, null, null)";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(insertClientSql);
//            preparedStatement.setLong(1, owner.getId());
            preparedStatement.setString(1, driver.getName());
            preparedStatement.setString(2, driver.getEmail());
            preparedStatement.setString(3, driver.getPassword());
            preparedStatement.setString(4, driver.getPhoneNumber());
            preparedStatement.setInt(5, 2);
            preparedStatement.execute();
        } catch (SQLException e)    {
            e.printStackTrace();
        }
    }


    public List<Client> getAllClients()
    {
        String selectSql = "SELECT * FROM user";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Client> clients = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("user_id");
                var name = resultSet.getString("user_name");
                var email = resultSet.getString("email");
                var password = resultSet.getString("user_password");
                var phoneNumber = resultSet.getString("phone_number");
                var user_type = resultSet.getInt("user_type");
                var address_id = resultSet.getInt("address_id");
                var cart_id = resultSet.getInt("cart_id");

                if(user_type==1)
                {
                    Client client = new Client(id, name, email, password, phoneNumber);
                    clients.add(client);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return clients;
    }

    public List<Owner> getAllOwners()
    {
        String selectSql = "SELECT * FROM user";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Owner> owners = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("user_id");
                var name = resultSet.getString("user_name");
                var email = resultSet.getString("email");
                var password = resultSet.getString("user_password");
                var phoneNumber = resultSet.getString("phone_number");
                var user_type = resultSet.getInt("user_type");
                var address_id = resultSet.getInt("address_id");
                var cart_id = resultSet.getInt("cart_id");

                if(user_type==2)
                {
                    Owner owner = new Owner.Builder(email, password).withName(name).withPhoneNumber(phoneNumber).withId(id).build();
                    owners.add(owner);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return owners;
    }

    public List<Review> getAllReviews()
    {
        String selectSql = "SELECT * FROM review";
        Connection connection = DatabaseConfiguration.getDatabaseConnection();
        List<Review> reviews = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(selectSql);

            while (resultSet.next())    {
                var id = resultSet.getLong("review_id");
                var user_id = resultSet.getLong("user_id");
                var restaurant_id = resultSet.getLong("restaurant_id");
                var review_text = resultSet.getString("review_text");
                var review_date = resultSet.getDate("review_date");
                var nr_stars = resultSet.getInt("nr_stars");

                var user = getClient(user_id);
                var restaurant = getRestaurant(restaurant_id);

                Review review = new Review.Builder(user, restaurant, nr_stars).withText(review_text).withDate((Date) review_date).build();
                reviews.add(review);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return reviews;
    }




    public void deleteAddress(Long address_id)
    {
        try {
            String deleteSql = "delete from address where address_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, address_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
             throwables.printStackTrace();
        }
    }
    public void deleteClient(Long client_id)
    {
        try {
            String deleteSql = "delete from user where user_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, client_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void deleteDish(Long dish_id)
    {
        try {
            String deleteSql = "delete from dish where dish_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, dish_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void deleteDriver(Long driver_id)
    {
        try {
            String deleteSql = "delete from user where user_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, driver_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void deleteMenu(Long menu_id)
    {
        try {
            String deleteSql = "delete from menu where menu_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, menu_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void deleteOrder(Long order_id)
    {
        try {
            String deleteSql = "delete from order_table where order_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, order_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void deleteOwner(Long owner_id)
    {
        try {
            String deleteSql = "delete from user where owner_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, owner_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void deleteRestaurant(Long restaurant_id)
    {
        try {
            String deleteSql = "delete from restaurant where restaurant_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, restaurant_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void deleteReview(Long review_id)
    {
        try {
            String deleteSql = "delete from review where review_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(deleteSql);
            preparedStatement.setLong(1, review_id);
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }

    public void editAddress(Address address)
    {
        try {
            String editSql = "update address set street=?, house_number=? where address_id = ?";
            Connection connection = DatabaseConfiguration.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(editSql);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getHouse_number());
            preparedStatement.setLong(3, address.getId());
            preparedStatement.execute();
        }
        catch (SQLException throwables)
        {
            throwables.printStackTrace();
        }
    }


}
