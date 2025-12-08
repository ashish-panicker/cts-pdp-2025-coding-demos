package org.example.jpa.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.PersistenceContext;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.service.ServiceRegistry;

/*
 * JPAUtil is Loaded
 * Initializes the emp field
 * Executes Persistence.createEntityManagerFactory("myPU")
 * This in turn kicks of the JPA Bootstrap process
 *
 */
public class JPAUtil {

    /*
     * Loads the JPA provider (ServiceLoader)
     * Loads the persistence.xml file from resources/META-INF
     * Select the persistence unit named myPU
     * Call the JPA Provider to actually build the EntityManagerFactory
     *
     * This is the JPA provider
     *  org.hibernate.jpa.HibernatePersistenceProvider
     * listed under <provider> tag in persitence.xml
     *
     * Loading perssitence.xml
     * Scan the classpath for META-INF/persistence.xml
     * Once the file is found parse for the persistence-unit tag
     * From that persistence unit block
     * - extract all entity classes
     * - extract the JDBC properties
     * - extract the hibernate properties
     * These three become the big part of config map which is passed on to Hibernate
     *
     * What does HibernatePersistenceProvider do?
     * 1. Build a ServiceRegistry
     * 2. Build Metadata/Metamodel
     * 3. Build SessionFactory
     * 4. Wrap SessionFactory in JPA
     */
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("myPU");

    // EntityManager is not thread-safe
    // One unit of work has One EntityManager
    // Hibernate creates a Session object
    // This session object contains
    // - a JDBC connection
    // - a persistence context
    // A persistenceContext
    // - is a map inside your EntityManager that stores all the entities that are currently managed
    // - acts as a first level cache
    // - track states of entities [persistent, detached, transient]
    // https://github.com/hibernate/hibernate-orm/blob/main/hibernate-core/src/main/java/org/hibernate/engine/internal/StatefulPersistenceContext.java
    public static EntityManager entityManager() {
        return emf.createEntityManager();
    }

    public static void shutdown() {
        emf.close();
    }
}
