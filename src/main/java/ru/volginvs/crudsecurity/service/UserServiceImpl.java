package ru.volginvs.crudsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volginvs.crudsecurity.dao.RoleDao;
import ru.volginvs.crudsecurity.dao.UserDao;
import ru.volginvs.crudsecurity.model.Role;
import ru.volginvs.crudsecurity.model.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    @Transactional
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUserList() {
        return userDao.getUserList();
    }

    @Override
    @Transactional
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        userDao.removeById(id);
    }

    @Override
    @Transactional
    public void addRoleToUserByRoleName(User user, String rolename) {
        Role role = roleDao.getRoleByName(rolename);
        user.addRole(role);
    }
}
