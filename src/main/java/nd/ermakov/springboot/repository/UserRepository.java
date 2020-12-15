package nd.ermakov.springboot.repository;

import nd.ermakov.springboot.model.User;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserRepository {
    private Map<String, User> storage = new ConcurrentHashMap<>();

    public User findByName(String name) {
        return storage.get(name);
    }

    public void save(User user) {
        storage.put(user.getName(), user);
    }
}
