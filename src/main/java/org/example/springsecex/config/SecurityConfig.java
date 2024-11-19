package org.example.springsecex.config;

import org.example.springsecex.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private UserDetailService userDetailService;

    @Bean
    //building our own security filter
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //http.csrf(Customizer->Customizer.disable()); //csrf token disabled
        http.authorizeHttpRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated()); //authorization activated
        http.formLogin(Customizer.withDefaults()); //login form present
        http.httpBasic(Customizer.withDefaults()); //so that postman requests for login are accepted
        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        //authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        authProvider.setPasswordEncoder(new BCryptPasswordEncoder(8));
        authProvider.setUserDetailsService(userDetailService);
        return authProvider;
    }
}
