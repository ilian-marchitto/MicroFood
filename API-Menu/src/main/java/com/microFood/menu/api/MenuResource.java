package com.microFood.menu.api;

import com.microFood.menu.domaine.Menu;
import com.microFood.menu.service.MenuService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Point d'entrée REST pour la gestion des menus.
 * <p>
 * Cette ressource expose les services de création, consultation, modification
 * et suppression de menus via le protocole HTTP en format JSON.
 * </p>
 *
 * @author Heather Burbeck
 * @version 1.0
 */
@Path("/menus")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class MenuResource {

    @Inject
    private MenuService menuService;

    /**
     * Récupère la liste de tous les menus.
     * * @return Une réponse HTTP 200 contenant la liste des menus.
     */
    @GET
    public Response getMenus() {
        return Response.ok(menuService.getAllMenu()).build();
    }

    /**
     * Récupère un menu par son identifiant.
     * * @param id Identifiant du menu dans l'URL.
     * @return 200 OK avec le menu, ou 404 NOT FOUND si l'ID est inconnu.
     */
    @GET
    @Path("/{id}")
    public Response getMenu(@PathParam("id") Integer id) {
        return menuService.getMenu(id)
                .map(menu -> Response.ok(menu).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    /**
     * Ajoute un nouveau menu dans le système.
     * * @param menu Objet Menu désérialisé du JSON reçu.
     * @return 201 CREATED en cas de succès, ou 400 BAD REQUEST si les données sont invalides.
     */
    @POST
    public Response addMenu(Menu menu) {
        try {
            menuService.createMenu(menu);
            return Response.status(Response.Status.CREATED).entity(menu).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Modifie un menu existant.
     * * @param id   Identifiant du menu à modifier (via l'URL).
     * @param menu Nouvelles données du menu (via le corps de la requête).
     * @return 200 OK ou 400 BAD REQUEST en cas d'erreur métier.
     */
    @PUT
    @Path("/{id}")
    public Response updateMenu(@PathParam("id") Integer id, Menu menu) {
        try {
            menu.setId(id);
            menuService.updateMenu(menu);
            return Response.ok(menu).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    /**
     * Supprime un menu du système.
     * * @param id Identifiant du menu à supprimer.
     * @return 204 NO CONTENT en cas de succès, ou 400 BAD REQUEST si le menu n'existe pas.
     */
    @DELETE
    @Path("/{id}")
    public Response deleteMenu(@PathParam("id") Integer id) {
        try {
            menuService.deleteMenu(id);
            return Response.noContent().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}