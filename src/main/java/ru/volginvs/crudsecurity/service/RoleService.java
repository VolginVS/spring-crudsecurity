package ru.volginvs.crudsecurity.service;

import ru.volginvs.crudsecurity.model.Role;
import ru.volginvs.crudsecurity.model.User;

import java.util.Set;

public interface RoleService {
    void addRoleToUserByRoleName(User user, String rolename);
    Role getByName(String name);
    Set<Role> getAllRoles();

}
