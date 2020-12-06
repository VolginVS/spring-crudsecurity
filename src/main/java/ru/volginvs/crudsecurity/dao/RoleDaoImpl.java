package ru.volginvs.crudsecurity.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.volginvs.crudsecurity.model.Role;

@Repository
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRoleByName(String name) {
        return sessionFactory.getCurrentSession().createQuery("from Role where name = :name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
