package Classes;
import java.util.*;

public class User {
    protected String name;
    protected String surname;
    protected String username;
    protected String email;
    protected String password;
    protected String phoneNumber;
    protected Integer id;


    public static class Builder{
        private User user = new User();

        public Builder(String email, String password){
            user.email = email;
            user.password=password;
        }
        public Builder withName(String name){
            user.name=name;
            return this;
        }
        public Builder withSurname(String surname){
            user.surname=surname;
            return this;
        }
        public Builder withEmail (String email){
            user.email=email;
            return this;
        }
        public Builder withUsername (String username){
            user.username=username;
            return this;
        }
        public Builder withPhoneNumber (String phoneNumber){
            user.phoneNumber=phoneNumber;
            return this;
        }

        public User build()
        {
            return this.user;
        }
    }

    protected User(){

    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
