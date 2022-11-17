package de.hsos.swa.cocktail.ECB.gateway;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import de.hsos.swa.cocktail.ECB.control.dto.CocktailsDTO;
import de.hsos.swa.cocktail.ECB.entity.Barkeeper;

@ApplicationScoped
public class CocktailRepository implements Barkeeper {
    private final String PATH = "https://www.thecocktaildb.com/api/json/v1/1/";

    @Override
    public List<CocktailsDTO> getCocktailByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CocktailsDTO> getIngredientByName(String ingredient) {
        // TODO Auto-generated method stub
        return null;
    }

}
