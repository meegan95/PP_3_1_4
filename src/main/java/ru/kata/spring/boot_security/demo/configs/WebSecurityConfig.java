package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SuccessUserHandler successUserHandler;
    private final UserDetailsService usersDetailsService;

    public WebSecurityConfig(SuccessUserHandler successUserHandler, UserDetailsService usersDetailsService) {
        this.successUserHandler = successUserHandler;
        this.usersDetailsService = usersDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/auth/registration", "/error").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginProcessingUrl("/process_login")
                .successHandler(successUserHandler)
                .failureUrl("/auth/login?error")
                .and().logout().logoutUrl("/logout");
    }

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usersDetailsService)
                .passwordEncoder(getPasswordEncoder())
        ;
    }

    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}