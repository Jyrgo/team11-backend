package ee.taltech.team_11_backend.pojo;

import ee.taltech.team_11_backend.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LoginDetails {
    private String username;
    private String token;
    private List<String> roles;
    private Role role;
}
