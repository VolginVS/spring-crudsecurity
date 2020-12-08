package ru.volginvs.crudsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volginvs.crudsecurity.dao.RoleDao;
import ru.volginvs.crudsecurity.model.Role;
import ru.volginvs.crudsecurity.model.User;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional(readOnly = true)
    public Role getByName(String name) {
        return roleDao.getByName(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    @Transactional
    public void addRoleToUserByRoleName(User user, String rolename) {
        Role role = getByName(rolename);
        user.getRoles().add(role);
    }

}
