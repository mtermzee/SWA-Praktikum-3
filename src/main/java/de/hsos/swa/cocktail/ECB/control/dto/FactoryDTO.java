package de.hsos.swa.cocktail.ECB.control.dto;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class FactoryDTO {

    public FactoryDTO() {
    }

    public List<CocktailDTO> getDataFromJSON(JsonObject json) {
        JsonArray drinks = json.getJsonArray("drinks");
        ArrayList<CocktailDTO> cocktails = new ArrayList<>();
        for (Object o : drinks) {
            cocktails.add(getCocktailInfolFromJsonObject((JsonObject) o));
        }
        return cocktails;
    }

    private CocktailDTO getCocktailInfolFromJsonObject(JsonObject cocktailJSON) {
        CocktailDTO cocktail = new CocktailDTO();
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

    public List<IngredientDTO> getDataFromJSONForIngredients(JsonObject json) {
        JsonArray drinks = json.getJsonArray("ingredients");
        ArrayList<IngredientDTO> ingredients = new ArrayList<>();
        for (Object o : drinks) {
            ingredients.add(getIngredientInfolFromJsonObject((JsonObject) o));
        }
        return ingredients;
    }

    private IngredientDTO getIngredientInfolFromJsonObject(JsonObject cocktailJSON) {
        IngredientDTO ingredient = new IngredientDTO();
        ingredient.setName(cocktailJSON.getString("strIngredient"));
        ingredient.setDescription(cocktailJSON.getString("strDescription"));
        return ingredient;
    }

}
