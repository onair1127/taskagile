package com.taskagile.infrastructure.repository;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public abstract class HibernateSupport {

     EntityManager entityManager;

     HibernateSupport(EntityManager entityManager) {
          this.entityManager = entityManager;
     }

     Session getSession() {
          return entityManager.unwrap(Session.class);
     }
}
