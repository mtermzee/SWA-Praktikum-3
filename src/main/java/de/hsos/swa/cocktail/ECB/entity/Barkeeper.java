package de.hsos.swa.cocktail.ECB.entity;

import java.util.List;

import de.hsos.swa.mocktail.ECB.entity.Ingredient;

public interface Barkeeper {
    public List<Cocktail> getCocktailByName(String name);

    public List<Ingredient> getIngredientByName(String ingredient);
}
