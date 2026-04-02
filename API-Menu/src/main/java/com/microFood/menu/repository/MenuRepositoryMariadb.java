package com.microFood.menu.repository;

import com.microFood.menu.domaine.Menu;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class MenuRepositoryMariadb implements MenuRepositoryInterface {

    @Override
    public void create(Menu menu) {

    }

    @Override
    public Menu read(Integer id) {
        return null;
    }

    @Override
    public void update(Menu menu) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public List<Menu> readAllMenu() {
        return List.of();
    }
}
