package de.hsos.swa.mocktail.ECB.control.ingredient;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mocktail.ECB.entity.Barkeeper;
import de.hsos.swa.mocktail.ECB.entity.Ingredient;

@ApplicationScoped
public class IngredientService {
    @Inject
    Barkeeper barkeeper;

    public Ingredient getIngredientById(int id) {
        return barkeeper.getIngredientById(id);
    }

    public List<Ingredient> getIngredients() {
        return barkeeper.getIngredients();
    }

    public int createIngredient(String name) {
        return barkeeper.createIngredient(name);
    }

    public boolean addIngredientToMocktail(int ingredientID, int mocktailID) {
        return barkeeper.addIngredientToMocktail(ingredientID, mocktailID);
    }

    public boolean addIngredientToMocktail(List<Integer> ingredientIDs, int mocktailID) {
        return barkeeper.addIngredientToMocktail(ingredientIDs, mocktailID);
    }

    public boolean updateIngredient(int id, String name) {
        return barkeeper.updateIngredient(id, name);
    }

    public boolean deleteIngredient(int id) {
        return barkeeper.deleteIngredient(id);
    }

    public boolean removeIngredientFromMocktail(int ingredientID, int mocktailID) {
        return barkeeper.removeIngredientFromMocktail(ingredientID, mocktailID);
    }

}
