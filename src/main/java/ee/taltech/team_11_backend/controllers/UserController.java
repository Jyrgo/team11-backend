package ee.taltech.team_11_backend.controllers;

import ee.taltech.team_11_backend.security.MyUser;
import ee.taltech.team_11_backend.security.MyUserDetailService;
import ee.taltech.team_11_backend.Services.UserService;
import ee.taltech.team_11_backend.exception.MyBadRequestException;
import ee.taltech.team_11_backend.pojo.LoginDetails;
import ee.taltech.team_11_backend.pojo.UserDTO;
import ee.taltech.team_11_backend.security.JwtTokenProvider;
import ee.taltech.team_11_backend.security.UserSessionHolder;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private MyUserDetailService myUserDetailService;
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("register")
    public void register(@RequestBody UserDTO userDto) {
        if (userDto.getUsername() == null) {
            throw new MyBadRequestException();
        }
        if (userDto.getPassword() == null) {
            throw new MyBadRequestException();
        }
        userService.saveUser(userDto);
    }

    @PostMapping("login")
    public LoginDetails login(@RequestBody UserDTO userDto) {
        if (userDto.getUsername() == null) {
            throw new MyBadRequestException();
        }
        if (userDto.getPassword() == null) {
            throw new MyBadRequestException();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword()));
        final UserDetails userDetails = myUserDetailService.loadUserByUsername(userDto.getUsername());
        final String token = jwtTokenProvider.generateToken(userDetails);
        MyUser myUser = (MyUser) userDetails;
        return new LoginDetails(myUser.getUsername(), token, toAuthorities(myUser), myUser.getRole());
    }

    @GetMapping("me")
    public MyUser me() {
        return UserSessionHolder.getLoggedInUser();
    }

    private List<String> toAuthorities(MyUser myUser) {
        return myUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

}
