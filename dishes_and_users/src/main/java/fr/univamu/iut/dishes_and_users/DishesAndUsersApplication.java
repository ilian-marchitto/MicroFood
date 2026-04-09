package fr.univamu.iut.dishes_and_users;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Disposes;
import jakarta.enterprise.inject.Produces;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
@ApplicationScoped
public class DishesAndUsersApplication extends Application {

    @Produces
    private DishesAndUsersRepositoryInterface openDbConnection(){
        DishesAndUsersRepositoryMariadb db = null;

        try{
            db = new DishesAndUsersRepositoryMariadb("jdbc:mariadb://mysql-fan2jul.alwaysdata.net/fan2jul_microfood_db", "fan2jul_microfood", "microfood_fan2jul");
        }
        catch (Exception e){
            System.err.println("==================================================");
            System.err.println("🔥 ERREUR CRITIQUE DE CONNEXION A LA BASE DE DONNEES 🔥");
            System.err.println("Cause exacte : " + e.getClass().getName());
            System.err.println("Message : " + e.getMessage());
            System.err.println("==================================================");
            e.printStackTrace();
        }
        return db;
    }

    private void closeDbConnection(@Disposes DishesAndUsersRepositoryInterface repo ) {
        repo.close();
    }
}
