package de.hsos.swa.cocktail.ECB.entity;

import java.util.List;

public interface Barkeeper {
    public List<Cocktail> getCocktailByName(String name);

    public List<Cocktail> getIngredientByName(String ingredient);
}
