package de.hsos.swa.cocktail.ECB.control.dto;

import java.util.ArrayList;
import java.util.List;

import de.hsos.swa.cocktail.ECB.entity.Cocktail;

public class CocktailsDTO {
    String name;
    List<String> ingredients = new ArrayList<>();
    String description;

    public CocktailsDTO() {
    }

    public CocktailsDTO(Cocktail cocktail) {
        this.name = cocktail.getName();
        this.ingredients = cocktail.getIngredients();
        this.description = cocktail.getDescription();
    }

    public static class CocktailKonverter {
        public static CocktailsDTO konvertiere(Cocktail cocktail) {
            return new CocktailsDTO(cocktail);
        }
    }

    public CocktailsDTO cocktailToDTO(Cocktail cocktail) {
        return CocktailKonverter.konvertiere(cocktail);
    }
}
