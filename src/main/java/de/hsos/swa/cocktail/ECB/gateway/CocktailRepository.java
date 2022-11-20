package de.hsos.swa.cocktail.ECB.gateway;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import de.hsos.swa.cocktail.ECB.control.dto.FactoryDTO;
import de.hsos.swa.cocktail.ECB.entity.Barkeeper;
import de.hsos.swa.cocktail.ECB.entity.Cocktail;
import de.hsos.swa.cocktail.ECB.gateway.api.CocktailAPI;
import de.hsos.swa.mocktail.ECB.entity.Ingredient;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class CocktailRepository implements Barkeeper {
    FactoryDTO factoryDTO = new FactoryDTO();

    @Override
    public List<Cocktail> getCocktailByName(String cocktail) {
        String url = CocktailAPI.PATH + CocktailAPI.COCKTAIL + cocktail;
        return factoryDTO.getDataFromJSON(getJSON(url));
    }

    @Override
    public List<Ingredient> getIngredientByName(String ingredient) {
        String url = CocktailAPI.PATH + CocktailAPI.INGREDIENT + ingredient;
        return factoryDTO.getDataFromJSONForIngredients(getJSON(url));
    }

    public JsonObject getJSON(String url) {
        Client client = ClientBuilder.newClient();
        WebTarget webTarget = client.target(url);
        JsonObject jsonObject = webTarget.request().accept(MediaType.APPLICATION_JSON).get(JsonObject.class);
        client.close();
        return jsonObject;
    }

}
