package sandoval.john.server.services;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.validation.Valid;
import sandoval.john.server.models.*;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private sandoval.john.server.repositories.UserRepository userRepository;

    // Register user and hash their password
    public User register(User newUser, BindingResult result) {
        if (userRepository.findByEmail(newUser.getEmail()).isPresent()) {
            result.rejectValue("email", "Email", "What are you doing?? you already registered");
        }
        if (!newUser.getPassword().equals(newUser.getConfirm())) {
            result.rejectValue("password", "Password", "Dude, learn to type! Your password dont match!");
        }
        if (result.hasErrors()) {
            return null;
        }
        String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
        System.out.println(hashed);
        newUser.setPassword(hashed);
        return userRepository.save(newUser);
    }

    // Login method:
    public User login(LoginCheck newLoginObject, BindingResult result) {
        Optional<User> user = userRepository.findByEmail(newLoginObject.getEmail());
        if (user.isEmpty()) {
            result.rejectValue("email", "logEmail", "Invalid credentials");
        } else if (!BCrypt.checkpw(newLoginObject.getPassword(), user.get().getPassword())) {
            result.rejectValue("password", "logPassword", "Invalid credentials");
        }
        if (result.hasErrors()) {
            return null;
        }
        return user.get();
    }


    // Find all users
    public List<User> allUsers() {
        return userRepository.findAll();
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Find one user
    public User findUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            return null;
        }
    }

    // Update user
    public void updateUser(@Valid User user) {
        userRepository.save(user);
    }
}