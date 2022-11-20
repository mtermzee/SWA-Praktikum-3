package de.hsos.swa.mocktail.ECB.entity;

import java.util.List;

public interface Barkeeper {
    // Mocktailes
    Mocktail getMocktailById(int id);

    List<Mocktail> getMocktails();

    List<Mocktail> searchMocktailByName(String name);

    int addMocktail(String name);

    boolean updateMocktail(int id, String name);

    boolean deleteMocktail(int id);

    // Ingredients
    Ingredient getIngredientById(int id);

    List<Ingredient> getIngredients();

    List<Ingredient> searchIngredientByName(String name);

    int createIngredient(String name);

    boolean addIngredientToMocktail(int ingredientID, int mocktailID);

    boolean addIngredientToMocktail(List<Integer> ingredientIDs, int mocktailID);

    boolean updateIngredient(int id, String name);

    boolean deleteIngredient(int id);

    boolean removeIngredientFromMocktail(int ingredientID, int mocktailID);

}
