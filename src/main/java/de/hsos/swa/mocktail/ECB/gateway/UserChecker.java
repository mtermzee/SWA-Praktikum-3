package de.hsos.swa.mocktail.ECB.gateway;

public interface UserChecker {

    public String getCurrentUser();

    public boolean hasRights(String role);
}
