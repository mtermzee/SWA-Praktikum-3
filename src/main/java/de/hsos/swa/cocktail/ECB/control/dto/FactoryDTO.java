package de.hsos.swa.cocktail.ECB.control.dto;

import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.cocktail.ECB.entity.Cocktail;
import de.hsos.swa.mocktail.ECB.entity.Ingredient;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class FactoryDTO {

    public FactoryDTO() {
    }

    public List<Cocktail> getDataFromJSON(JsonObject json) {
        JsonArray drinks = json.getJsonArray("drinks");
        ArrayList<Cocktail> cocktails = new ArrayList<>();
        for (Object o : drinks) {
            cocktails.add(getCocktailInfolFromJsonObject((JsonObject) o));
        }
        return cocktails;
    }

    private Cocktail getCocktailInfolFromJsonObject(JsonObject cocktailJSON) {
        Cocktail cocktail = new Cocktail();
        cocktail.setName(cocktailJSON.getString("strDrink"));
        cocktail.setDescription(cocktailJSON.getString("strInstructionsDE"));
        for (int i = 0; i < 10; i++) {
            String ingredient = cocktailJSON.getString("strIngredient" + String.valueOf(i + 1));
            if (ingredient != null) {
                cocktail.addIngredient(ingredient);
            }
        }
        return cocktail;
    }

    public List<Ingredient> getDataFromJSONForIngredients(JsonObject json) {
        JsonArray drinks = json.getJsonArray("ingredients");
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        for (Object o : drinks) {
            ingredients.add(getIngredientInfolFromJsonObject((JsonObject) o));
        }
        return ingredients;
    }

    private Ingredient getIngredientInfolFromJsonObject(JsonObject cocktailJSON) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(cocktailJSON.getString("strIngredient"));
        ingredient.setDescription(cocktailJSON.getString("strDescription"));
        return ingredient;
    }

}
