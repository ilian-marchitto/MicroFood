package fr.univamu.iut.commande;

import fr.univamu.iut.commande.repositories.OrderRepositoryInterface;
import fr.univamu.iut.commande.repositories.OrderRepositoryMariadb;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

/**
 * Configuration JAX-RS et gestion de l'injection de dépendances (CDI).
 */
@ApplicationPath("/api")
@ApplicationScoped
public class OrderApplication extends Application {

    @Produces
    private OrderRepositoryInterface openDbConnection() {
        String url = "jdbc:mariadb://mysql-sofiaach.alwaysdata.net/sofiaach_orders_db";
        String user = "sofiaach";
        String password = "03f861e78edb";
        return new OrderRepositoryMariadb(url, user, password);
    }

    private void closeDbConnection(@Disposes OrderRepositoryInterface orderRepo) {
        if (orderRepo != null) orderRepo.close();
    }
}