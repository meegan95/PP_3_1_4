package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.repositories.RolesRepository;

import java.util.List;

@Service
public class RolesService {

    private final RolesRepository rolesRepository;

    @Autowired
    public RolesService(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public List<Role> getRoles() {
        return rolesRepository.findAll();
    }


    public void saveRole(Role role) {
        rolesRepository.save(role);
    }

    public void removeRoleById(int id) {
        rolesRepository.deleteById(id);
    }

}
