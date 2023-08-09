package com.example.l5e1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        return http.httpBasic(Customizer.withDefaults()).authorizeRequests().anyRequest()
//                .hasAnyAuthority("read","write")
//                .hasRole("ADMIN")
                .
         access("isAuthenticated() and hasAuthority('read')" )
        .and().
                build();



//                .authorizeHttpRequests()

    }

    @Bean
    public UserDetailsService userDetailsService(){
        var user = new InMemoryUserDetailsManager();
        var user1 = User.withUsername("Usopp")
                .password(passwordEncoder().encode("1234"))
                .authorities("read").build();
        var user2 = User.withUsername("Luffy")
                .password(passwordEncoder().encode("6234"))
                .authorities("write").build();

        user.createUser(user1);
        user.createUser(user2);

        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
