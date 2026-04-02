package com.microFood.menu.repository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlatRepositoryService implements PlatRepositoryInterface{
    @Override
    public boolean checkPlatsExists(Integer idPlat) {
        return false;
    }
}
