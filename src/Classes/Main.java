public class Main {

    public static void main(String[] args)
    {
        Client myClient = new Client.Builder("stan@gmail.com", "1234").build();
        System.out.println(myClient.toString());
    }
}
