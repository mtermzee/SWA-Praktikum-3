package de.hsos.swa.cocktail.ECB.boundry;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import de.hsos.swa.cocktail.ECB.control.cocktail.CocktailService;
import de.hsos.swa.cocktail.ECB.control.dto.CocktailDTO;
import de.hsos.swa.cocktail.ECB.control.dto.IngredientDTO;

@Path("/cocktails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CocktailResource {
    @Inject
    CocktailService cocktailService;

    @GET
    @Path("{name}")
    public Response getCocktailByName(@PathParam("name") String name) {
        List<CocktailDTO> cocktails = cocktailService.getCocktailByName(name);

        if (!cocktails.isEmpty()) {
            return Response.ok(cocktails).build();
        }

        return Response.status(Status.NOT_FOUND).entity("No cocktails was found").type("text/plain").build();
    }

    @GET
    @Path("ingredients/{name}")
    public Response getIngredientByName(@PathParam("name") String name) {
        List<IngredientDTO> ingredients = cocktailService.getIngredientByName(name);

        if (!ingredients.isEmpty()) {
            return Response.ok(ingredients).build();
        }

        return Response.status(Status.NOT_FOUND).entity("No ingredients was found").type("text/plain").build();
    }
}
