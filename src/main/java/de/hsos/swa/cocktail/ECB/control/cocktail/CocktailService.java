package de.hsos.swa.cocktail.ECB.control.cocktail;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.cocktail.ECB.entity.Barkeeper;
import de.hsos.swa.cocktail.ECB.entity.Cocktail;
import de.hsos.swa.mocktail.ECB.entity.Ingredient;

@ApplicationScoped
public class CocktailService {

    @Inject
    Barkeeper barkeeper;

    public List<Cocktail> getCocktailByName(String name) {
        return barkeeper.getCocktailByName(name);
    }

    public List<Ingredient> getIngredientByName(String ingredient) {
        return barkeeper.getIngredientByName(ingredient);
    }

}
