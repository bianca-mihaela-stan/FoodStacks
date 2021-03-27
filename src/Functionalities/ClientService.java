package Functionalities;

import Classes.Restaurant;

import java.awt.color.ICC_ColorSpace;

public class ClientService {

    private static ClientService instance;

    private ClientService()
    {

    }

    public static ClientService getInstance()
    {
        if(instance==null)
        {
            instance = new ClientService();
        }
        return instance;
    }

    public void order()
    {

    }
    void cancelOrder()
    {

    }
    void editOrder()
    {

    }
    void addReview()
    {

    }
    void addAddress()
    {

    }
    void addToFavourites(Restaurant restaurant)
    {

    }
}
