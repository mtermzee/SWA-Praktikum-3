package de.hsos.swa.cocktail.ECB.gateway;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import de.hsos.swa.cocktail.ECB.control.dto.CocktailsDTO;
import de.hsos.swa.cocktail.ECB.entity.Barkeeper;
import de.hsos.swa.cocktail.ECB.entity.Cocktail;
import de.hsos.swa.cocktail.ECB.gateway.api.CocktailAPI;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class CocktailRepository implements Barkeeper {
    CocktailsDTO cocktailsDTO = new CocktailsDTO();

    @Override
    public List<Cocktail> getCocktailByName(String name) {
        String url = CocktailAPI.PATH + CocktailAPI.SEARCH + name;
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(url);
        JsonObject jsonObject = webTarget.request().accept(MediaType.APPLICATION_JSON).get(JsonObject.class);
        client.close();
        return cocktailsDTO.getDataFromJSON(jsonObject);
    }

    @Override
    public List<Cocktail> getIngredientByName(String ingredient) {
        // TODO Auto-generated method stub
        return null;
    }

}
