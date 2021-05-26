package Classes;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

public class Address implements Cloneable{
    protected County county;
    protected String city;
    protected Integer sector;
    protected String street;
    protected String number;
    protected String block;
    protected String entrance;        // scara blocului
    protected Integer floor;
    protected Integer apartmentNumber;
    protected Long id;

    private static AtomicLong addressID = new AtomicLong(0);

    private static Long newID()
    {
        return addressID.incrementAndGet();
    }

    Address()
    {
        id=newID();
    }

    public Address(County county, String city, Integer sector,
                   String street, String number, String block, String entrance,
                   Integer floor, Integer apartmentNumber)
    {
        this.id = newID();
        this.county=county;
        this.city=city;
        this.sector=sector;
        this.street=street;
        this.number=number;
        this.block=block;
        this.entrance=entrance;
        this.floor=floor;
        this.apartmentNumber=apartmentNumber;
    }

    public Address clone() throws CloneNotSupportedException
    {
        Address address = new Address();
        address.county=county;
        address.city=city;
        address.sector=sector;
        address.street=street;
        address.number = number;
        address.block=block;
        address.entrance=entrance;
        address.floor=floor;
        address.apartmentNumber=apartmentNAddr
        return address;
    }

    public static class Builder{
        private Address address = new Address();

        public Address build(){
            return this.address;
        }
        public Builder(County county, String city, String street, String number){
            address.county=county;
            address.city = city;
            address.street = street;
            address.number = number;
        }
        public Address.Builder withBlock(String block){
            address.block=block;
            return this;
        }
        public Address.Builder withSector(Integer sector){
            address.sector=sector;
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
        public Address.Builder withApartmentNumber (Integer apatmentNumber){
            address.apartmentNumber=apatmentNumber;
            return this;
        }
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



    @Override
    public String toString() {
        return "Address{" +
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
}
