package ee.taltech.team_11_backend.repository;

import ee.taltech.team_11_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
