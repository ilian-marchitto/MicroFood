package fr.univamu.iut.commande.repositories;

import fr.univamu.iut.commande.entities.Order;
import java.util.ArrayList;

/**
 * Interface définissant les opérations de persistance pour les commandes.
 */
public interface OrderRepositoryInterface {
    void close();
    Order getOrder(int id);
    ArrayList<Order> getAllOrders();
    boolean addOrder(Order order);
    boolean updateOrder(int id, Order order);
    boolean deleteOrder(int id);
}