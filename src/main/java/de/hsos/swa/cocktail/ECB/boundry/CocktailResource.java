package de.hsos.swa.cocktail.ECB.boundry;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import de.hsos.swa.cocktail.ECB.control.cocktail.CocktailService;

@Path("/cocktails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CocktailResource {
    @Inject
    CocktailService cocktailService;
}
