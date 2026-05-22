package cafeteria.person;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // Attributes
    protected String fullName;
    protected String phone;
    protected String email;
    protected String address;
    
    // Constructors
    public Person() {
        this.fullName = "";
        this.phone = "";
        this.email = "";
        this.address = "";
    }
    
    public Person(String fullName, String phone, String email) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = "";
    }
    
    public Person(String fullName, String phone, String email, String address) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }
    
    // Getters and Setters
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    // Abstract methods
    public abstract void displayInfo();
    public abstract String getRole();
    
    // Common methods
    public boolean validateContact() {
        return phone != null && !phone.trim().isEmpty() && 
               email != null && email.contains("@");
    }
    
    @Override
    public String toString() {
        return String.format("%s (%s)", fullName, getRole());
    }
}