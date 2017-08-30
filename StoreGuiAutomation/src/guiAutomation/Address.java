package guiAutomation;

public class Address {
    public final String street1;
    public final String street2;
    public final String city;
    public final String state;
    public final String zipCode;

    public Address(
            String street1,
            String street2,
            String city,
            String state,
            String zipCode) {
        this.street1 = street1.substring(0, Math.min(street1.length(), 100));
        this.street2 = street2.substring(0, Math.min(street2.length(), 100));
        this.city = city.substring(0, Math.min(city.length(), 100));
        this.state = state.substring(0, Math.min(state.length(), 2));
        this.zipCode = zipCode.substring(0, Math.min(zipCode.length(), 10));
    }
}