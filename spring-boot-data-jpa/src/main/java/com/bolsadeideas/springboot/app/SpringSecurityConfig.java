package com.bolsadeideas.springboot.app;

import com.bolsadeideas.springboot.app.auth.handler.LoginSuccessHandler;
import com.bolsadeideas.springboot.app.models.service.JpaUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
public class SpringSecurityConfig {

    @Autowired
    private LoginSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JpaUserDetailService userDetailService;

    @Autowired
    public void userDetailsService(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(successHandler)
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/error_404")
                .and().build();
    }
}