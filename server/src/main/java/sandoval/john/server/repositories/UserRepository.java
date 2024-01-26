package sandoval.john.server.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import sandoval.john.server.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // This method will find a user by their id
    User findById(long id);

    // This method will find a user by their email
    Optional<User> findByEmail(String email);

}