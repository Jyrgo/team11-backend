package ee.taltech.team_11_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private MyUserDetailService myUserDetailsService;
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
    @Resource
    private JwtRequestFilter jwtRequestFilter;

    /**
     * authentication configuration
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService)
                .and()
                .inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("admin")).authorities(Roles.ROLE_ADMIN, Roles.ROLE_USER)
//                .and()
//                .withUser("fred").password(passwordEncoder().encode("fred")).authorities(Roles.ROLE_USER)
        ;
    }

    /**
     * http security configuration
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // cross site request forgery, it's a must if we use cookies
                .headers().httpStrictTransportSecurity().disable() // if this is not disabled your https frontend must have https (not http) on backend
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS) // this is a must for API, API just returns answers, doesn't know anything about any sessions (front-end manages that)
                .and()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .authorizeRequests()
//                .antMatchers("/insurance").hasRole("ROLE_USER")
//                .antMatchers(HttpMethod.POST, "/insurance").hasRole("ROLE_ADMIN")
                .antMatchers("/game").permitAll()
                .antMatchers("/users/register").permitAll() //so guest can register
                .antMatchers("/users/login").permitAll() //so guest can login
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .logout().logoutUrl("/logout");
    }

    /**
     * spring does not allow plain text passwords
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * authentication manager is used as entrance to creating authentication
     */
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
