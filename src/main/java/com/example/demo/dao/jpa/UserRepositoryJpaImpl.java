package com.example.demo.dao.jpa;

import com.example.demo.dao.repository.UserRepository;
import com.example.demo.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryJpaImpl implements UserRepository {

    @PersistenceContext // runtime de yönetilen transaction'u enjekte eder.
    private EntityManager entityManager;

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUserName(String username) {
        return entityManager.createQuery("select u from User u where u.username = :username", User.class).setParameter("username", username).getResultList().stream().findFirst().orElse(null);
    }

    @Override
    public void create(User user) {
        String queryForUsers = "insert into Users(USERNAME,PASSWORD,FIRST_NAME,LAST_NAME,ENABLED) values(?,?,?,?,true)";
        entityManager.createNativeQuery(queryForUsers)
                .setParameter(1, user.getUsername())
                .setParameter(2, "{noop}" + user.getPassword())
                .setParameter(3, user.getFirstName())
                .setParameter(4, user.getLastName())
                .executeUpdate();

        String queryForAuth = "insert into authorities(USERNAME,AUTHORITY) values(?,?)";
        entityManager.createNativeQuery(queryForAuth)
                .setParameter(1, user.getUsername())
                .setParameter(2, "ROLE_USER")
                .executeUpdate();
    }

    @Override
    public User update(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void delete(Long id) {
        entityManager.remove(entityManager.getReference(User.class, id));
    }
}
