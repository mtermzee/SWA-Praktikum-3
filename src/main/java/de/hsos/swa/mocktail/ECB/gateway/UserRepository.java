package de.hsos.swa.mocktail.ECB.gateway;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;

import de.hsos.swa.mocktail.ECB.control.user.UserPost;
import de.hsos.swa.mocktail.ECB.entity.User;

@ApplicationScoped
public class UserRepository implements UserChecker, UserPost {
    Map<Integer, User> users = new HashMap<>();
    int currentUserID;

    public UserRepository() {
        User user = new User();
        user.setId(0);
        user.setName("admin");
        // user.setRole("admin");
        currentUserID = 0;
        users.put(user.getId(), user);
    }

    @Override
    public String getCurrentUser() {
        return users.get(currentUserID).getName();
    }

    @Override
    public boolean hasRights(String role) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean changeUser(String username) {
        for (User user : users.values()) {
            if (user.getName().equals(username)) {
                currentUserID = user.getId();
                return false;
            }
        }
        User newUser = new User();
        newUser.setId(users.size());
        newUser.setName(username);
        users.put(newUser.getId(), newUser);
        currentUserID = newUser.getId();
        return true;
    }
}
