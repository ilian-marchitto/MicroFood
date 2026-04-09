package fr.univamu.iut.dishes_and_users;

import java.io.Closeable;
import java.sql.*;
import java.util.ArrayList;

public class DishesAndUsersRepositoryMariadb implements DishesAndUsersRepositoryInterface, Closeable {

    protected Connection dbConnection;

    public DishesAndUsersRepositoryMariadb(String infoConnection, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.mariadb.jdbc.Driver");
        dbConnection = DriverManager.getConnection(infoConnection, user, pwd);
    }

    @Override
    public void close() {
        try {
            dbConnection.close();
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public boolean createDish(int id, String name, String description, double price) {
        String query = "INSERT INTO Dish (id, name, description, price) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, description);
            ps.setDouble(4, price);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public Dish getDish(int id) {
        Dish selectedDish = null;
        String query = "SELECT * FROM Dish WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                selectedDish = new Dish(id, result.getString("name"), result.getString("description"), result.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedDish;
    }

    @Override
    public ArrayList<Dish> getAllDishes() {
        ArrayList<Dish> listDishes = new ArrayList<>();
        String query = "SELECT * FROM Dish";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                // result.getInt("id") au lieu de getString
                Dish d = new Dish(result.getInt("id"), result.getString("name"), result.getString("description"), result.getDouble("price"));
                listDishes.add(d);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listDishes;
    }

    @Override
    public boolean updateDish(int id, String name, String description, double price) {
        String query = "UPDATE Dish SET name=?, description=?, price=? WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setInt(4, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteDish(int id) {
        String query = "DELETE FROM Dish WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    @Override
    public boolean createUser(int id, String lastname, String firstname, String email, String address) {
        String query = "INSERT INTO User (id, lastname, firstname, email, address) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, lastname);
            ps.setString(3, firstname);
            ps.setString(4, email);
            ps.setString(5, address);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public User getUser(int id) {
        User selectedUser = null;
        String query = "SELECT * FROM User WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet result = ps.executeQuery();
            if(result.next()) {
                selectedUser = new User(
                        id,
                        result.getString("lastname"),
                        result.getString("firstname"),
                        result.getString("email"),
                        result.getString("address")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return selectedUser;
    }

    @Override
    public ArrayList<User> getAllUsers() {
        ArrayList<User> listUsers = new ArrayList<>();
        String query = "SELECT * FROM User";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ResultSet result = ps.executeQuery();
            while (result.next()) {
                listUsers.add(new User(
                        result.getInt("id"),
                        result.getString("lastname"),
                        result.getString("firstname"),
                        result.getString("email"),
                        result.getString("address")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listUsers;
    }

    @Override
    public boolean updateUser(int id, String lastname, String firstname, String email, String address) {
        String query = "UPDATE User SET lastname=?, firstname=?, email=?, address=? WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setString(1, lastname);
            ps.setString(2, firstname);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setInt(5, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String query = "DELETE FROM User WHERE id=?";
        try (PreparedStatement ps = dbConnection.prepareStatement(query)) {
            ps.setInt(1, id);
            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }
}