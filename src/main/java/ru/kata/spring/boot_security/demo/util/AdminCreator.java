package ru.kata.spring.boot_security.demo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;

import java.util.HashSet;

@Component
public class AdminCreator {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;

    @Autowired
    public AdminCreator(UsersRepository usersRepository, RolesRepository rolesRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
    }

    @Bean
    public void adminCreate(){
        try {
            HashSet<Role> roles = new HashSet<>();
            roles.add(new Role("ROLE_ADMIN"));
            roles.add(new Role("ROLE_USER"));
            usersRepository.save(new User("admin", 1999, "admin", roles));
        } catch (Exception e) {/*ignore*/}
    }
    @Bean
    public void userCreate(){
        try {
            HashSet<Role> roles = new HashSet<>();
            roles.add(new Role("ROLE_USER"));
            usersRepository.save(new User("user", 1999, "user", roles));
        } catch (Exception e) {/*ignore*/}
    }
}
