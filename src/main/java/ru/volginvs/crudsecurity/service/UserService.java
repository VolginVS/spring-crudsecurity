package ru.volginvs.crudsecurity.service;

import ru.volginvs.crudsecurity.model.User;

import java.util.List;

public interface UserService {

    public void save(User user);
    public User getUserById(Long id);
    public List<User> getUserList();
    public void update(User user);
    public void removeById(Long id);
    public void addRoleToUserByRoleName(User user, String rolename);
}
