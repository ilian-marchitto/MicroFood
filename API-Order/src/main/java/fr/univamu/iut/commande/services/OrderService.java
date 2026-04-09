package fr.univamu.iut.commande.services;

import fr.univamu.iut.commande.entities.Order;
import fr.univamu.iut.commande.entities.OrderLine;
import fr.univamu.iut.commande.repositories.OrderRepositoryInterface;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import java.util.ArrayList;

/**
 * Service gérant la logique métier des commandes et l'agrégation de données externes.
 */
public class OrderService {
    protected OrderRepositoryInterface orderRepo;
    private final String MENU_API_URL = "http://localhost:3004/menus";

    public OrderService(OrderRepositoryInterface orderRepo) {
        this.orderRepo = orderRepo;
    }

    public String getAllOrdersJSON() {
        ArrayList<Order> allOrders = orderRepo.getAllOrders();
        Client client = ClientBuilder.newClient();
        for (Order order : allOrders) {
            enrichOrder(order, client);
        }
        client.close();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(allOrders);
        } catch (Exception e) { return null; }
    }

    public Order getOrderWithExternalDetails(int id) {
        Order myOrder = orderRepo.getOrder(id);
        if (myOrder != null) {
            Client client = ClientBuilder.newClient();
            enrichOrder(myOrder, client);
            client.close();
        }
        return myOrder;
    }

    private void enrichOrder(Order order, Client client) {
        for (OrderLine line : order.getLignes()) {
            try {
                OrderLine ext = client.target(MENU_API_URL).path(String.valueOf(line.getMenuId()))
                        .request(MediaType.APPLICATION_JSON).get(OrderLine.class);
                if (ext != null) {
                    line.setMenuNom(ext.getMenuNom());
                    line.setPrixUnitaire(ext.getPrixUnitaire());
                    line.setPrixLigne(line.getQuantite() * ext.getPrixUnitaire());
                }
            } catch (Exception e) { System.err.println("API Menu inaccessible"); }
        }
    }
}