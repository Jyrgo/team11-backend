package ee.taltech.team_11_backend.security;

import ee.taltech.team_11_backend.model.User;
import ee.taltech.team_11_backend.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

@Service
@AllArgsConstructor
public class MyUserDetailService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findByUsername(username);
        if (isEmpty(users)) {
            throw new UsernameNotFoundException(format("username not found: %s", username));
        }
        User user = users.get(0); //this application doesn't protect against duplicate users
        return new MyUser(user.getUsername(), user.getPassword(), getAuthorities(user), user.getRole(), user.getId());
    }

    private List<SimpleGrantedAuthority> getAuthorities(User user) {
        return getRoles(user)
                .map(Role::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    /**
     * if user is admin, then they get all the roles in application
     */
    private Stream<Role> getRoles(User user) {
        if (user.getRole().isAdmin()) {
            return Arrays.stream(Role.values());
        }
        return Stream.of(user.getRole());
    }

}
