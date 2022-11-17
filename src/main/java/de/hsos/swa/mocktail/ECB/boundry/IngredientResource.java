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
import de.hsos.swa.mocktail.ECB.entity.Ingredient;

@Path("/ingredients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class IngredientResource {
    // Inject - for dependency injection Initialize the MocktailRepository
    @Inject
    IngredientService ingredientService;

    @PostConstruct
    public void init() {
        // add data
        // Wassermelonen-Mocktail
        ingredientService.createIngredient("Melonenfleisch");
        ingredientService.createIngredient("Orangensaft");
        ingredientService.createIngredient("Limettesaft");
        ingredientService.createIngredient("Erdbeersaft");
        ingredientService.createIngredient("Eiswürfel");
    }

    @GET
    public Response getIngredients() {
        List<Ingredient> ingredients = this.ingredientService.getIngredients();
        if (!ingredients.isEmpty())
            return Response.ok(ingredients).build();

        return Response.status(Status.NOT_FOUND).entity("No ingredients was found").type("text/plain").build();
    }

    @GET
    @Path("{id}")
    public Response getIngredientById(@PathParam("id") int id) {
        Ingredient ingredient = this.ingredientService.getIngredientById(id);
        if (ingredient != null)
            return Response.ok(ingredient).build();

        return Response.status(Status.NOT_FOUND).entity("No ingredient was found").type("text/plain").build();
    }

    @POST
    @Path("{ingredient}")
    public Response createIngredient(@PathParam("ingredient") String ingredient) {
        int id = this.ingredientService.createIngredient(ingredient);
        if (id != 0)
            return Response.ok(id).entity("Ingredient has been added successfully").type("text/plain").build();

        return Response.status(Status.NOT_FOUND).entity("Ingredient was not added successfully").type("text/plain")
                .build();
    }

    @POST
    @Path("{ingredient}/{id}")
    public Response addIngredientToMocktail(@PathParam("id") int ingredientID,
            @QueryParam("mocktailID") int mocktailID) {
        boolean result = this.ingredientService.addIngredientToMocktail(ingredientID, mocktailID);
        if (result)
            return Response.ok(result).entity("Ingredient has been added to Mocktail successfully").type("text/plain")
                    .build();

        return Response.status(Status.NOT_FOUND).entity("Ingredient was not added to Mocktail successfully")
                .type("text/plain")
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateIngredient(@PathParam("id") int id, @QueryParam("name") String name) {
        if (name != null) {
            boolean result = this.ingredientService.updateIngredient(id, name);
            if (result)
                return Response.ok(result).entity("Ingredient has been updated successfully").type("text/plain")
                        .build();
        }
        return Response.status(Status.NOT_FOUND).entity("Ingredient was not updated successfully").type("text/plain")
                .build();
    }

    @DELETE
    @Path("{id}")
    public Response deleteIngredient(@PathParam("id") int id) {
        boolean result = this.ingredientService.deleteIngredient(id);
        if (result)
            return Response.ok(result).entity("Ingredient has been deleted successfully").type("text/plain").build();

        return Response.status(Status.NOT_FOUND).entity("Ingredient was not deleted successfully").type("text/plain")
                .build();
    }

}
