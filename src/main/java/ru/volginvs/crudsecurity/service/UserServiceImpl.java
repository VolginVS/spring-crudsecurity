package ru.volginvs.crudsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.volginvs.crudsecurity.dao.RoleDao;
import ru.volginvs.crudsecurity.dao.UserDao;
import ru.volginvs.crudsecurity.model.Role;
import ru.volginvs.crudsecurity.model.User;

import javax.persistence.PersistenceContext;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Transactional
    public void setUserRoles(User user) {
        user.setRoles(user
                .getRoles()
                .stream()
                .map(role -> roleDao.getByName(role.getName()))
                .collect(Collectors.toSet()));
    }

    @Override
    @Transactional
    public void save(User user) {
        setUserRoles(user); // Проверить, работает ли оно? Нужно ли вообще оно?
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
        //setUserRoles(user);
        userDao.update(user);
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        userDao.removeById(id);
    }


    @Transactional
    @Override
    public User getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    // ??? ты присераешь role к POJO объекту, но при этом не записываешь ее в базу данных
    @Override
    @Transactional
    public void addRoleToUserByRoleName(User user, String rolename) {
        Role role = roleDao.getByName(rolename);
        user.addRole(role);
    }

    @Override
    @Transactional
    public User editRoleSet(User user, String rolename) {
        setUserRoles(user);
        Set<Role> roles = user.getRoles();
        if (roles.contains(roleDao.getByName("ROLE_ADMIN"))) {
            if (rolename.equals("ROLE_USER")) {
                //roles.remove(roleDao.getByName("ROLE_ADMIN"));
            }
        } else {
            if (rolename.equals("ROLE_ADMIN")) {
                roles.add(roleDao.getByName("ROLE_ADMIN"));
            }
        }
       // update(user);
        return user;
    }
}
