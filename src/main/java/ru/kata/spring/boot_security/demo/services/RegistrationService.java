package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UsersRepository;


@Service
public class RegistrationService {

    private final UsersRepository usersRepository;

    @Autowired
    public RegistrationService(UsersRepository usersRepository
    ) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public void register(User user) {
        user.addRole(new Role("ROLE_USER"));
        usersRepository.save(user);
    }

}
