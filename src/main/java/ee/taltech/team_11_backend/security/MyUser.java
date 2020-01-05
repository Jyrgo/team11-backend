package ee.taltech.team_11_backend.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
public class MyUser extends User {

    private Long id;
    private Role role;

    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Role role, Long id) {
        super(username, password, authorities);
        this.role = role;
        this.id = id;
    }

    /**
     * This constructor had to be overriden, however, is never used.
     */
    public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

}
