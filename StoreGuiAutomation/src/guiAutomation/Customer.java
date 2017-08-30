package guiAutomation;

public class Customer {
    public final String firstName;
    public final String middleName;
    public final String lastName;
    public final Address mailingAddress;
    public final Address billingAddress;
    public final String phoneNumber;
    public final String email;
    public final String username;
    public final String password;

    public Customer(
            String firstName,
            String middleName,
            String lastName,
            Address mailingAddress,
            Address billingAddress,
            String phoneNumber,
            String email,
            String username,
            String password) {
        this.firstName = firstName.substring(0, Math.min(firstName.length(), 50));
        this.middleName = middleName.substring(0, Math.min(middleName.length(), 50));
        this.lastName = lastName.substring(0, Math.min(lastName.length(), 50));
        this.mailingAddress = mailingAddress;
        this.billingAddress = billingAddress;
        this.phoneNumber = phoneNumber.substring(0, Math.min(phoneNumber.length(), 50));
        this.email = email.substring(0, Math.min(email.length(), 100));
        this.username = username.substring(0, Math.min(username.length(), 50));
        this.password = password.substring(0, Math.min(password.length(), 50));
    }
}