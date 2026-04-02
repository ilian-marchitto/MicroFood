package com.microFood.menu.domaine;

import java.util.List;

/**
 * Représente l'entité métier Menu au sein du microservice de gestion des menus.
 * <p>
 * Cette classe est un Plain Old Java Object (POJO) utilisé pour modéliser un menu
 * composé de plusieurs plats, identifiés par leurs références externes.
 * </p>
 * * @author Heather Burbeck
 * @version 1.0
 */
public class Menu {

    /** Identifiant unique du menu en base de données. */
    private Integer id;

    /** Nom commercial du menu (ex: "Menu Gourmand"). */
    private String name;

    /** Prix de vente du menu. */
    private Double price;

    /** Liste des identifiants techniques des plats composant ce menu.
     * Ces IDs font référence aux entités gérées par le microservice "Plats". */
    private List<Integer> idsDish;

    /**
     * Constructeur par défaut (obligatoire pour la désérialisation JSON-B).
     */
    public Menu() {}

    /**
     * Constructeur complet pour l'initialisation d'un menu.
     * * @param id      L'identifiant unique.
     * @param name    Le nom du menu.
     * @param price   Le prix du menu.
     * @param idsDish La liste des IDs des plats associés.
     */
    public Menu(Integer id, String name, Double price, List<Integer> idsDish) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.idsDish = idsDish;
    }

    /**
     * @return L'identifiant technique du menu.
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id L'identifiant technique à affecter.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Le nom du menu.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Le nom commercial à affecter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Le prix du menu.
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price Le prix à affecter.
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * Récupère la liste des IDs de plats associés à ce menu.
     * * @return Une liste d'entiers représentant les IDs des plats.
     */
    public List<Integer> getIdsDish() {
        return idsDish;
    }

    /**
     * @param idsDish La liste des IDs de plats à associer au menu.
     */
    public void setIdsDish(List<Integer> idsDish) {
        this.idsDish = idsDish;
    }
}