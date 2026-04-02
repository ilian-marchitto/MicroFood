package com.microFood.menu.repository;

/**
 * Interface définissant le contrat de communication avec le microservice externe des Plats.
 * <p>
 * Dans une architecture microservices, cette interface permet de valider l'existence
 * de ressources gérées par un autre composant sans dupliquer leurs données métier.
 * </p>
 * * @author Heather Burbeck
 * @version 1.0
 */
public interface PlatRepositoryInterface {

    /**
     * Vérifie si un plat existe dans le catalogue du microservice distant.
     * <p>
     * Cette méthode interroge l'API "Plats et Utilisateurs" pour s'assurer de l'intégrité
     * référentielle lors de la création ou de la modification d'un menu.
     * </p>
     * * @param idPlat L'identifiant technique du plat à vérifier.
     * @return true si le plat est reconnu par le service distant, false sinon.
     */
    boolean checkPlatsExists(Integer idPlat);
}