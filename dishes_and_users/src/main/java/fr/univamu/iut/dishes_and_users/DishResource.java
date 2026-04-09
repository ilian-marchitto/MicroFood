package fr.univamu.iut.dishes_and_users;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/dishes")
public class DishResource {

    private DishService service;

    public DishResource() {}

    public @Inject DishResource(DishesAndUsersRepositoryInterface repo) {
        this.service = new DishService(repo);
    }

    @POST
    @Consumes("application/json")
    public Response createDish(Dish dish) {
        if(service.createDish(dish)) {
            return Response.status(Response.Status.CREATED).entity("created").build();
        }
        return Response.serverError().build();
    }

    @GET
    @Produces("application/json")
    public String getAllDishes() {
        return service.getAllDishesJSON();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    // Le paramètre id de l'URL est automatiquement traduit en int par Jakarta
    public String getDish(@PathParam("id") int id) {
        String result = service.getDishJSON(id);
        if(result == null) throw new NotFoundException();
        return result;
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateDish(@PathParam("id") int id, Dish dish) {
        if(!service.updateDish(id, dish)) throw new NotFoundException();
        return Response.ok("updated").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDish(@PathParam("id") int id) {
        if(!service.deleteDish(id)) throw new NotFoundException();
        return Response.noContent().build();
    }
}