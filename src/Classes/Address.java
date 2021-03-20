package Classes;
import java.util.*;

public class Address {
    protected Country country;
    protected County county;
    protected String city;
    protected Integer sector;
    protected String street;
    protected String number;

    @Override
    public String toString() {
        return "Address{" +
                "country=" + country +
                ", county=" + county +
                ", city='" + city + '\'' +
                ", sector=" + sector +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", block='" + block + '\'' +
                ", entrance='" + entrance + '\'' +
                ", floor=" + floor +
                ", apartmentNumber=" + apartmentNumber +
                '}';
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public County getCounty() {
        return county;
    }

    public void setCounty(County county) {
        this.county = county;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getSector() {
        return sector;
    }

    public void setSector(Integer sector) {
        this.sector = sector;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getEntrance() {
        return entrance;
    }

    public void setEntrance(String entrance) {
        this.entrance = entrance;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(Integer apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    protected String block;
    protected String entrance;        // scara blocului
    protected Integer floor;
    protected Integer apartmentNumber;

    public static class Builder{
        private Address address = new Address();

        public Address build(){
            return this.address;
        }
        public Builder(String city, String street, String number){
            address.city = city;
            address.street = street;
            address.number = number;
        }
        public Address.Builder withCounty(County county){
            address.county=county;
            return this;
        }
        public Address.Builder withBlock(String block){
            address.block=block;
            return this;
        }
        public Address.Builder withEntrance (String entrance){
            address.entrance=entrance;
            return this;
        }
        public Address.Builder withFloor (Integer floor){
            address.floor=floor;
            return this;
        }
        public Address.Builder withApatmentNumber (Integer apatmentNumber){
            address.apartmentNumber=apatmentNumber;
            return this;
        }
    }
}
