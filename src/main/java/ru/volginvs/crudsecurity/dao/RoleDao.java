package ru.volginvs.crudsecurity.dao;

import ru.volginvs.crudsecurity.model.Role;

public interface RoleDao {
    public Role getRoleByName(String name);
}
