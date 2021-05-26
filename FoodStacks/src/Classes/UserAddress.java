package Classes;

public class UserAddress extends Address{
    private AddressIdentifier addressIdentifier;

    public AddressIdentifier getAddressIdentifier() {
        return addressIdentifier;
    }

    @Override
    public String toString() {
        return "UserAddress{" +
                ", county=" + county +
                ", city='" + city + '\'' +
                ", sector=" + sector +
                ", street='" + street + '\'' +
                ", number='" + number + '\'' +
                ", block='" + block + '\'' +
                ", entrance='" + entrance + '\'' +
                ", floor=" + floor +
                ", apartmentNumber=" + apartmentNumber +
                ", addressIdentifier=" + addressIdentifier +
                '}';
    }

    public void setAddressIdentifier(AddressIdentifier addressIdentifier) {
        this.addressIdentifier = addressIdentifier;
    }
}
