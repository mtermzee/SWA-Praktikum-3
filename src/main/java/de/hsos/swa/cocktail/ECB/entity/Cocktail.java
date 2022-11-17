package de.hsos.swa.cocktail.ECB.entity;

import java.util.ArrayList;
import java.util.List;

public class Cocktail {
    int id;
    String name;
    List<String> ingredients = new ArrayList<>();
    String description;

    public Cocktail() {
    }

    public Cocktail(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Cocktail(int id, String name, List<String> ingredients, String description) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Cocktail [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", description=" + description
                + "]";
    }

}
