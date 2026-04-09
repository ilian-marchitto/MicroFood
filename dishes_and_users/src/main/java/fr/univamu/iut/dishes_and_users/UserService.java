package fr.univamu.iut.dishes_and_users;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class UserService {

    protected DishesAndUsersRepositoryInterface repo;

    public UserService(DishesAndUsersRepositoryInterface repo) {
        this.repo = repo;
    }

    public boolean createUser(User user) {
        return repo.createUser(user.getId(), user.getLastname(), user.getFirstname(), user.getEmail(), user.getAddress());
    }

    public String getAllUsersJSON() {
        ArrayList<User> allUsers = repo.getAllUsers();
        String result = null;
        try(Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allUsers);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getUserJSON(int id) {
        String result = null;
        User myUser = repo.getUser(id);
        if(myUser != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myUser);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public boolean updateUser(int id, User user) {
        return repo.updateUser(id, user.getLastname(), user.getFirstname(), user.getEmail(), user.getAddress());
    }

    public boolean deleteUser(int id) {
        return repo.deleteUser(id);
    }
}