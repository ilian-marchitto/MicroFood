package fr.univamu.iut.dishes_and_users;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import java.util.ArrayList;

public class DishService {

    protected DishesAndUsersRepositoryInterface repo;

    public DishService(DishesAndUsersRepositoryInterface repo) {
        this.repo = repo;
    }

    public boolean createDish(Dish dish) {
        return repo.createDish(dish.getId(), dish.getName(), dish.getDescription(), dish.getPrice());
    }

    public String getAllDishesJSON() {
        ArrayList<Dish> allDishes = repo.getAllDishes();
        String result = null;
        try(Jsonb jsonb = JsonbBuilder.create()) {
            result = jsonb.toJson(allDishes);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    public String getDishJSON(int id) {
        String result = null;
        Dish myDish = repo.getDish(id);
        if(myDish != null) {
            try (Jsonb jsonb = JsonbBuilder.create()) {
                result = jsonb.toJson(myDish);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return result;
    }

    public boolean updateDish(int id, Dish dish) {
        return repo.updateDish(id, dish.getName(), dish.getDescription(), dish.getPrice());
    }

    public boolean deleteDish(int id) {
        return repo.deleteDish(id);
    }
}