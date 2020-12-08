package ru.volginvs.crudsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.volginvs.crudsecurity.dao.RoleDao;
import ru.volginvs.crudsecurity.model.Role;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }
}
