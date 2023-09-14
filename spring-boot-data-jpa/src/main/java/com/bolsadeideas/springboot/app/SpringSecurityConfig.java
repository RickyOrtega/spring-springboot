package com.bolsadeideas.springboot.app;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig{

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception{
        PasswordEncoder encoder = passwordEncoder();
/*        User.UserBuilder users = User.builder().passwordEncoder(password -> {
            return encoder.encode(password);
        });*/

        User.UserBuilder users = User.builder().passwordEncoder(encoder::encode);

        builder.inMemoryAuthentication()
                .withUser(users
                        .username("admin")
                        .password("12345")
                        .roles("ADMIN", "USER"))
                .withUser(users
                        .username("andres")
                        .password("12345")
                        .roles("USER"));

    }

}

/*
    @Bean
    public UserDetailsService userDetailsService()throws Exception{

        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User
                .withUsername("jhon")
                .password(passwordEncoder().encode("12345"))
                .roles("USER")
                .build());

        manager.createUser(User
                .withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMIN","USER")
                .build());

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> {
                    try {
                        authz.requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
                                .requestMatchers("/uploads/**").hasAnyRole("USER")
                                .requestMatchers("/ver/**").hasRole("USER")
                                .requestMatchers("/factura/**").hasRole("ADMIN")
                                .requestMatchers("/form/**").hasRole("ADMIN")
                                .requestMatchers("/eliminar/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                                .and()
                                .formLogin().permitAll()
                                .and()
                                .logout().permitAll();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        return http.build();

    }
}*/
