package nd.ermakov.springboot.service;

import nd.ermakov.springboot.model.User;
import nd.ermakov.springboot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthStatus authorize(String name, String password) {
        User user = userRepository.findByName(name);
        if (user == null) {
            return AuthStatus.NOT_FOUND;
        }
        if (!user.getPassword().equals(password)) {
            return AuthStatus.WRONG_PASSWORD;
        }
        return AuthStatus.OK;
    }

    public AuthStatus register(String name, String password) {
        if (userRepository.findByName(name) != null) {
            return AuthStatus.ALREADY_EXISTS;
        }
        userRepository.save(new User(name, password));
        return AuthStatus.CREATED;
    }
}
