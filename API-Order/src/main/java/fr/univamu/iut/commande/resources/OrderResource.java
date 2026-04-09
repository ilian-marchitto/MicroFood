package fr.univamu.iut.commande.resources;

import fr.univamu.iut.commande.entities.Order;
import fr.univamu.iut.commande.repositories.OrderRepositoryInterface;
import fr.univamu.iut.commande.services.OrderService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

/**
 * Ressource REST exposant les endpoints pour la gestion des commandes.
 */
@Path("/orders")
@ApplicationScoped
public class OrderResource {
    private OrderService service;

    public OrderResource(){}

    @Inject
    public OrderResource(OrderRepositoryInterface orderRepo) {
        this.service = new OrderService(orderRepo);
    }

    @GET
    @Produces("application/json")
    public String getAllOrders() {
        return service.getAllOrdersJSON();
    }

    @GET
    @Path("{id}")
    @Produces("application/json")
    public String getOrder(@PathParam("id") int id) {
        Order order = service.getOrderWithExternalDetails(id);
        if (order == null) throw new NotFoundException();
        try (Jsonb jsonb = JsonbBuilder.create()) {
            return jsonb.toJson(order);
        } catch (Exception e) { return null; }
    }
}