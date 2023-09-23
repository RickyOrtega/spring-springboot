package com.bolsadeideas.springboot.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

/*    @Bean
    public UserDetailsService userDetailsService() throws Exception {

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .roles("USER").build());

        manager.createUser(User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN", "USER").build());

        return manager;
    }*/

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        PasswordEncoder encoder = passwordEncoder();

        User.UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
                .withUser(users
                        .username("admin")
                        .password("12345")
                        .roles("ADMIN", "USER"))
                .withUser(users
                        .username("ricky")
                        .password("12345")
                        .roles("USER"));

    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
                            .requestMatchers("/ver/**").hasAnyRole("USER")
                            .requestMatchers("/uploads/**").hasAnyRole("USER")
                            .requestMatchers("/form/**").hasAnyRole("ADMIN")
                            .requestMatchers("/eliminar/**").hasAnyRole("ADMIN")
                            .requestMatchers("/factura/**").hasAnyRole("ADMIN")
                            .anyRequest().authenticated();
                }).formLogin((login) -> {
                    login
                            .permitAll()
                            .loginPage("/login");
                })
                .logout(LogoutConfigurer::permitAll);

        http.exceptionHandling((exception) -> {
            exception.accessDeniedPage("/error_403");
        });

        return http.build();
    }
}