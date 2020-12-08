package ru.volginvs.crudsecurity.service;

import ru.volginvs.crudsecurity.model.User;

import java.util.List;

public interface UserService {

    void save(User user);
    User getUserById(Long id);
    List<User> getUserList();
    void update(User user);
    void removeById(Long id);
    User getByUsername(String username);
    void addRoleToUserByRoleName(User user, String rolename);
}
