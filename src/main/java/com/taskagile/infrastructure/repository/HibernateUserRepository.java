package com.taskagile.infrastructure.repository;

import javax.persistence.EntityManager;

import com.taskagile.domain.model.user.User;
import com.taskagile.domain.model.user.UserRepository;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

/**
 * 이게 진짜 Reposiory
 * 도메인에 있는 UserRepository는 그냥 추상화
 * 이거 상태 관련된거 철저 입문으로 공부하자
 */

@Repository
public class HibernateUserRepository extends HibernateSupport implements UserRepository {

     public HibernateUserRepository(EntityManager entityManager) {
          super(entityManager);
     }

     @Override
     public User findByUsername(String username) {
          Query<User> query = getSession().createQuery(
               "from User where username = :username", User.class);
          query.setParameter("username", username);
          return query.uniqueResult();
     }

     @Override
     public User findByEmailAddress(String emailAddress) {
          Query<User> query = getSession().createQuery(
               "from User where emailAddress = :emailAddress", User.class);
          query.setParameter("emailAddress", emailAddress);
          return query.uniqueResult();
     }

     @Override
     public void save(User user) {
          entityManager.persist(user);
          entityManager.flush();
     }
}
