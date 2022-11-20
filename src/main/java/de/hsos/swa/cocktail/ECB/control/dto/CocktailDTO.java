package de.hsos.swa.cocktail.ECB.control.dto;

import java.util.ArrayList;
import java.util.List;

public class CocktailDTO {
    String name;
    List<String> ingredients = new ArrayList<>();
    String description;

    public CocktailDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
