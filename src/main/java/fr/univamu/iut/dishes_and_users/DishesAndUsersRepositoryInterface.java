package fr.univamu.iut.dishes_and_users;

import java.util.ArrayList;

public interface DishesAndUsersRepositoryInterface {

    public void close();

    public boolean createDish(int id, String name, String description, double price);
    public Dish getDish(int id);
    public ArrayList<Dish> getAllDishes();
    public boolean updateDish(int id, String name, String description, double price);
    public boolean deleteDish(int id);

    public boolean createUser(int id, String lastname, String firstname, String email, String address);
    public User getUser(int id);
    public ArrayList<User> getAllUsers();
    public boolean updateUser(int id, String lastname, String firstname, String email, String address);
    public boolean deleteUser(int id);
}