package dao;

public class Customer {

    private String customerID;
    private String lastName;
    private String firstName;


    public Customer(String id, String lastName, String firstName){
        customerID = id;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public String getID(){
        return customerID;
    }

}
