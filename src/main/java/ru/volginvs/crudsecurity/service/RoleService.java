package ru.volginvs.crudsecurity.service;

import ru.volginvs.crudsecurity.model.Role;
import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();
}
