package de.hsos.swa.mocktail.ECB.boundry;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.mocktail.ECB.control.ingredient.IngredientService;
import de.hsos.swa.mocktail.ECB.control.mocktail.MocktailService;
import de.hsos.swa.mocktail.ECB.entity.Mocktail;

@Path("/mocktails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class MocktailResource {
    // Inject - for dependency injection Initialize the MocktailRepository
    @Inject
    MocktailService mocktailService;

    @Inject
    IngredientService ingredientService;

    @PostConstruct
    public void init() {
        // add data
        int m1 = mocktailService.addMocktail("Sex on the Beach");

        // sex on the beach rezept
        // add rezept to Mocktail
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Wodka"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Pfirsichlikör"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Cranberry Direktsaft"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Orangensaft"), m1);
        ingredientService.addIngredientToMocktail(ingredientService.createIngredient("Eiswürfel"), m1);
    }

    @GET
    public Response getMocktails() {
        List<Mocktail> mocktails = this.mocktailService.getMocktails();
        if (!mocktails.isEmpty())
            return Response.ok(mocktails).build();

        return Response.status(Status.NOT_FOUND).entity("No mocktails was found").type("text/plain").build();
    }

    @GET
    @Path("{id}")
    public Response getMocktailById(@PathParam("id") int id) {
        Mocktail mocktail = this.mocktailService.getMocktailById(id);
        if (mocktail != null)
            return Response.ok(mocktail).build();

        return Response.status(Status.NOT_FOUND).entity("No mocktail was found").type("text/plain").build();
    }

    @POST
    @Path("{mocktail}")
    public Response addMocktail(@PathParam("mocktail") String mocktail) {
        if (mocktail != null) {
            int id = this.mocktailService.addMocktail(mocktail);
            if (id != -1)
                return Response.ok(id).entity("Mocktail has been added successfully").type("text/plain").build();
        }
        return Response.status(Status.BAD_REQUEST).entity("Mocktail was not added successfully").type("text/plain")
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateMocktail(@PathParam("id") int id, @QueryParam("mocktail") String mocktail) {
        if (mocktail != null) {
            boolean updated = this.mocktailService.updateMocktail(id, mocktail);
            if (updated)
                return Response.ok().entity("Mocktail has been updated successfully").type("text/plain").build();
        }
        return Response.status(Status.BAD_REQUEST).entity("Mocktail was not updated successfully").type("text/plain")
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteMocktail(@PathParam("id") int id) {
        boolean deleted = this.mocktailService.deleteMocktail(id);
        if (deleted)
            return Response.ok().entity("Mocktail has been deleted successfully").type("text/plain").build();

        return Response.status(Status.BAD_REQUEST).entity("Mocktail was not deleted successfully").type("text/plain")
                .build();
    }

}
