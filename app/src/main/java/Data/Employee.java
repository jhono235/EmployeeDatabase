package Data;

public class Employee {


    private static String firstName;
    private static String lastName;
    private static String streetAddress;
    private static String city;
    private static String state;
    private static String zip;
    private static String taxID;
    private static String position;
    private static String department;

    public Employee(){

    }

    public Employee(String firstName,
                    String lastName,
                    String streetAddress,
                    String city,
                    String state,
                    String zip,
                    String taxID,
                    String position,
                    String department){
        this.firstName = firstName;
        this.lastName = lastName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.taxID = taxID;
        this.position = position;
        this.department = department;
    }

    public static String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public static String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public static String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public static String getTaxID() {
        return taxID;
    }

    public void setTaxID(String taxID) {
        this.taxID = taxID;
    }

    public static String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public static String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }





}
