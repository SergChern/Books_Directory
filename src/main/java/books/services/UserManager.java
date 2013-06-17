package books.services;

import java.util.HashMap;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import books.server.HibernateUtil;
import books.server.User;

@Service
public class UserManager {
    private HashMap<String, User> users;

    public UserManager() {
        users = new HashMap<String, User>();
        users.put("user", new User("user", "u000", "ROLE_USER"));
        users.put("admin", new User("admin", "a999", "ROLE_USER, ROLE_ADMIN"));
    }

    public User getUser(String username) throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException(username + " not found");
        }
        HibernateUtil.setUsername(users.get(username));
        return users.get(username);
    }
}
