package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.Role;

import java.util.List;

public interface RolesService {

    List<Role> getRoles();

    Role getRoleByName(String name);

    void saveRole(Role role);

    void removeRoleById(int id);

}
