package fr.univamu.iut.dishes_and_users;

public class Dish {
    protected int id;
    protected String name;
    protected String description;
    protected double price;

    public Dish() {}

    public Dish(int id, String name, String description, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() {return description; }
    public double getPrice() { return price; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) {this.description = description; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Dish{id='" + id + "', name='" + name + "', description=" + description + "', price=" + price + '}';
    }
}