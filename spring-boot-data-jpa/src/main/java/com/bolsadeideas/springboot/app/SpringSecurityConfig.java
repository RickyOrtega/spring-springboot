package com.bolsadeideas.springboot.app;

import com.bolsadeideas.springboot.app.auth.handler.LoginSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private DataSource dataSource;

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(encoder)
                .usersByUsernameQuery("select username, password, enabled from users where username=?")
                .authoritiesByUsernameQuery("select u.username, a.authority from authorities a inner join users u on (a.user_id=u.id) where u.username=?")
                .and().build();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((authorize) -> {
                    authorize
                            .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
                            /*.requestMatchers("/ver/**").hasAnyRole("USER")
                            .requestMatchers("/uploads/**").hasAnyRole("USER")
                            .requestMatchers("/form/**").hasAnyRole("ADMIN")
                            .requestMatchers("/eliminar/**").hasAnyRole("ADMIN")
                            .requestMatchers("/factura/**").hasAnyRole("ADMIN")*/
                            .anyRequest().authenticated();
                }).formLogin((login) -> {
                    login
                            .permitAll()
                            .loginPage("/login")
                            .successHandler(loginSuccessHandler);
                })
                .logout(LogoutConfigurer::permitAll);

        http.exceptionHandling((exception) -> {
            exception.accessDeniedPage("/error_403");
        });

        return http.build();
    }
}