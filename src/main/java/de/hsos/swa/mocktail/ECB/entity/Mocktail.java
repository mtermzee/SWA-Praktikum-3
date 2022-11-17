package de.hsos.swa.mocktail.ECB.entity;

import java.util.ArrayList;
import java.util.List;

public class Mocktail {
    int id;
    String name;
    List<Ingredient> ingredients = new ArrayList<>();
    String author;

    public Mocktail() {
    }

    public Mocktail(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Mocktail(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }

    public Mocktail(int id, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        ingredients.add(ingredient);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean removeIngredient(int id) {
        for (Ingredient ingredient : ingredients) {
            if (ingredient.getId() == id) {
                ingredients.remove(ingredient);
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((ingredients == null) ? 0 : ingredients.hashCode());
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Mocktail other = (Mocktail) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (ingredients == null) {
            if (other.ingredients != null)
                return false;
        } else if (!ingredients.equals(other.ingredients))
            return false;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Mocktail [id=" + id + ", name=" + name + ", ingredients=" + ingredients + ", author=" + author + "]";
    }

}
