package ee.taltech.team_11_backend.Services;

import ee.taltech.team_11_backend.model.User;
import ee.taltech.team_11_backend.pojo.UserDTO;
import ee.taltech.team_11_backend.repository.UserRepository;
import ee.taltech.team_11_backend.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserDTO saveUser(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return userDto;
    }
}
