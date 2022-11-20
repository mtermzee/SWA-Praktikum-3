package de.hsos.swa.cocktail.ECB.control.cocktail;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.cocktail.ECB.control.dto.CocktailDTO;
import de.hsos.swa.cocktail.ECB.control.dto.IngredientDTO;
import de.hsos.swa.cocktail.ECB.entity.Barkeeper;

@ApplicationScoped
public class CocktailService {

    @Inject
    Barkeeper barkeeper;

    public List<CocktailDTO> getCocktailByName(String name) {
        return barkeeper.getCocktailByName(name);
    }

    public List<IngredientDTO> getIngredientByName(String ingredient) {
        return barkeeper.getIngredientByName(ingredient);
    }

}
