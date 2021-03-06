package ru.volginvs.crudsecurity.dao;

import ru.volginvs.crudsecurity.model.Role;

import java.util.Set;

public interface RoleDao {

    Role getByName(String name);
    Set<Role> getAllRoles();
}
