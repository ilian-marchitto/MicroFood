package fr.univamu.iut.dishes_and_users;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/users")
public class UserResource {

    private UserService service;

    public UserResource() {}

    public @Inject UserResource(DishesAndUsersRepositoryInterface repo) {
        this.service = new UserService(repo);
    }

    @POST
    @Consumes("application/json")
    public Response createUser(User user) {
        if(service.createUser(user)) {
            return Response.status(Response.Status.CREATED).entity("created").build();
        }
        return Response.serverError().build();
    }

    @GET
    @Produces("application/json")
    public String getAllUsers() {
        return service.getAllUsersJSON();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public String getUser(@PathParam("id") int id) {
        String result = service.getUserJSON(id);
        if(result == null) throw new NotFoundException();
        return result;
    }

    @PUT
    @Path("/{id}")
    @Consumes("application/json")
    public Response updateUser(@PathParam("id") int id, User user) {
        if(!service.updateUser(id, user)) throw new NotFoundException();
        return Response.ok("updated").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) {
        if(!service.deleteUser(id)) throw new NotFoundException();
        return Response.noContent().build();
    }
}