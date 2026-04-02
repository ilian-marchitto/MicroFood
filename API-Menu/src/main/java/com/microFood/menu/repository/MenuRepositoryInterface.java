package com.microFood.menu.repository;

import com.microFood.menu.domaine.Menu;
import java.util.List;

/**
 * Interface définissant le contrat d'accès aux données pour la persistance des menus.
 * <p>
 * Cette interface suit le pattern Repository et permet de réaliser les opérations
 * CRUD (Create, Read, Update, Delete) sur les menus stockés dans la base de données MariaDB.
 * </p>
 * * @author Heather Burbeck
 * @version 1.0
 */
public interface MenuRepositoryInterface {

    /**
     * Enregistre un nouveau menu dans le système de persistance.
     * * @param menu L'objet Menu contenant les informations à sauvegarder.
     */
    void create(Menu menu);

    /**
     * Récupère un menu spécifique à partir de son identifiant unique.
     * * @param id L'identifiant technique du menu recherché.
     * @return L'objet Menu correspondant, ou null si aucune correspondance n'est trouvée.
     */
    Menu read(Integer id);

    /**
     * Met à jour les informations d'un menu existant.
     * * @param menu L'objet Menu contenant les nouvelles données à enregistrer.
     */
    void update(Menu menu);

    /**
     * Supprime un menu du système de persistance.
     * * @param id L'identifiant unique du menu à supprimer.
     */
    void delete(Integer id);

    /**
     * Récupère la liste exhaustive de tous les menus enregistrés.
     * * @return Une Liste d'objets Menu. Si aucun menu n'existe, renvoie une liste vide.
     */
    List<Menu> readAllMenu();
}