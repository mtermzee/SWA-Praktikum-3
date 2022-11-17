package de.hsos.swa.cocktail.ECB.entity;

import java.util.List;

import de.hsos.swa.cocktail.ECB.control.dto.CocktailsDTO;

public interface Barkeeper {
    public List<CocktailsDTO> getCocktailByName(String name);

    public List<CocktailsDTO> getIngredientByName(String ingredient);
}
