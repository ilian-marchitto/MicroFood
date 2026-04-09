package fr.univamu.iut.dishes_and_users;

public class User {
    protected int id;
    protected String lastname;
    protected String firstname;
    protected String email;
    protected String address;

    public User() {}

    public User(int id, String lastname , String firstname, String email, String address) {
        this.id = id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.email = email;
        this.address = address;
    }

    public int getId() { return id; }
    public String getLastname() { return lastname; }
    public String getFirstname() { return firstname; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }

    public void setId(int id) { this.id = id; }
    public void setLastname(String lastname) { this.lastname = lastname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) {this.address = address; }

    @Override
    public String toString() {
        return "User{id='" + id + "', lastname='" + lastname +  "', firstname='" + firstname + "', email='" + email + "', address='" + address + "'}";
    }
}