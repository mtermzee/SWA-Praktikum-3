package de.hsos.swa.mocktail.ECB.control.mocktail;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsos.swa.mocktail.ECB.entity.Barkeeper;
import de.hsos.swa.mocktail.ECB.entity.Mocktail;

@ApplicationScoped
public class MocktailService {
    @Inject
    Barkeeper barkeeper;

    public Mocktail getMocktailById(int id) {
        return barkeeper.getMocktailById(id);
    }

    public List<Mocktail> getMocktails() {
        return barkeeper.getMocktails();
    }

    public int addMocktail(String name) {
        return barkeeper.addMocktail(name);
    }

    public boolean updateMocktail(int id, String name) {
        return barkeeper.updateMocktail(id, name);
    }

    public boolean deleteMocktail(int id) {
        return barkeeper.deleteMocktail(id);
    }
}
