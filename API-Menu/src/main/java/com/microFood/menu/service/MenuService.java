package com.microFood.menu.service;

import com.microFood.menu.domaine.Menu;
import com.microFood.menu.repository.MenuRepositoryInterface;
import com.microFood.menu.repository.PlatRepositoryInterface;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Service métier gérant la logique des menus.
 * <p>
 * Cette classe orchestre les opérations entre le stockage local (MariaDB) et
 * les vérifications de cohérence auprès du microservice externe des plats.
 * Elle garantit l'intégrité des données avant toute persistance.
 * </p>
 *
 * @author Heather Burbeck
 * @version 1.0
 */
@ApplicationScoped
public class MenuService {

    @Inject
    private MenuRepositoryInterface menuRepository;

    @Inject
    private PlatRepositoryInterface platRepository;

    /**
     * Valide la cohérence d'une liste de plats auprès du microservice externe.
     * * @param idsPlats Liste des identifiants de plats à vérifier.
     * @throws IllegalArgumentException si la liste est vide ou si un plat n'existe pas.
     */
    private void checkAllDishes(List<Integer> idsPlats) {
        if (idsPlats == null || idsPlats.isEmpty()) {
            throw new IllegalArgumentException("Un menu doit contenir au moins un plat.");
        }
        for (Integer idPlat : idsPlats) {
            if (!platRepository.checkPlatsExists(idPlat)) {
                throw new IllegalArgumentException("Le plat avec l'id " + idPlat + " n'existe pas.");
            }
        }
    }

    /**
     * Vérifie l'existence d'un menu avant une opération de modification ou suppression.
     * * @param idMenu Identifiant du menu à vérifier.
     * @throws IllegalArgumentException si le menu n'est pas trouvé.
     */
    private void checkMenuNotEmpty(Integer idMenu) {
        if (getMenu(idMenu).isEmpty()) {
            throw new IllegalArgumentException("Le menu avec l'id " + idMenu + " n'existe pas.");
        }
    }

    /**
     * Crée un nouveau menu après validation de l'existence de tous ses plats.
     * * @param menu L'objet menu à créer.
     */
    public void createMenu(Menu menu) {
        checkAllDishes(menu.getIdsDish());
        menuRepository.create(menu);
    }

    /**
     * Récupère un menu enveloppé dans un Optional pour une gestion sécurisée du null.
     * * @param idMenu Identifiant du menu.
     * @return Un Optional contenant le menu si trouvé, sinon un Optional vide.
     */
    public Optional<Menu> getMenu(Integer idMenu) {
        return Optional.ofNullable(menuRepository.read(idMenu));
    }

    /**
     * @return La liste de tous les menus disponibles.
     */
    public List<Menu> getAllMenu() {
        return menuRepository.readAllMenu();
    }

    /**
     * Supprime un menu après avoir vérifié son existence.
     * * @param idMenu Identifiant du menu à supprimer.
     */
    public void deleteMenu(Integer idMenu) {
        checkMenuNotEmpty(idMenu);
        menuRepository.delete(idMenu);
    }

    /**
     * Met à jour un menu existant en re-validant la liste des plats.
     * * @param menu L'objet menu mis à jour.
     */
    public void updateMenu(Menu menu) {
        checkMenuNotEmpty(menu.getId());
        checkAllDishes(menu.getIdsDish());
        menuRepository.update(menu);
    }
}