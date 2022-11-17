package de.hsos.swa.cocktail.ECB.control.cocktail;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.cocktail.ECB.entity.Barkeeper;

@ApplicationScoped
public class CocktailService {

    @Inject
    Barkeeper barkeeper;
}
