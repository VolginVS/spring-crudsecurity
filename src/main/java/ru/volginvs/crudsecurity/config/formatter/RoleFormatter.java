package ru.volginvs.crudsecurity.config.formatter;

import org.springframework.format.Formatter;
import ru.volginvs.crudsecurity.model.Role;

import javax.persistence.EntityManager;
import java.text.ParseException;
import java.util.Locale;

public class RoleFormatter implements Formatter<Role> {

    private EntityManager entityManager;

    public RoleFormatter(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role parse(String stringWithId, Locale locale) throws ParseException {
        return entityManager.find(Role.class, Long.parseLong(stringWithId));
    }

    @Override
    public String print(Role role, Locale locale) {
        return role.getId().toString();
    }
}
