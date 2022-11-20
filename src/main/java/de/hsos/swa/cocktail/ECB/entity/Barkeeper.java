package de.hsos.swa.cocktail.ECB.entity;

import java.util.List;

import de.hsos.swa.cocktail.ECB.control.dto.CocktailDTO;
import de.hsos.swa.cocktail.ECB.control.dto.IngredientDTO;

public interface Barkeeper {
    public List<CocktailDTO> getCocktailByName(String name);

    public List<IngredientDTO> getIngredientByName(String ingredient);
}
