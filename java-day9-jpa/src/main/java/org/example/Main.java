package org.example;

import org.example.jpa.entity.Employee;
import org.example.jpa.entity.JPAUtil;

public class Main {
    public static void main(String[] args) {
        try (var em = JPAUtil.entityManager()) {
            System.out.println("Creating employee.");
            var tx = em.getTransaction();
            var emp = Employee.builder().name("Ashish").salary(25000).build();
            tx.begin();
            em.persist(emp);
            /*
             * Calling the persist(), it does not generate sql immediately
             * It creates an InsertAction, and stores it in the action queue
             * Hibernate keeps track of all changes that needs to be implemented in the ActionQueue
             * - InsertAction
             * - UpdateAction
             * - DeleteAction
             * - CollectionUpdateAction
             * - CollectionRemoveAction
             * Later when the commit()/flush() is called it will convert these action to sql
             */
            tx.commit();

            System.out.println("Persisted: " + emp);

            var id = emp.getId();
            System.out.println("Fetching emp based on id: " + id);
            var empFromDb = em.find(Employee.class, id);
            System.out.println("Found employee: " + empFromDb);


            // In this code block, an entity is loaded, and some values are modified.
            // Hibernate is able to detect these changes and update the database accordingly
            // when the commit() is executed. This is done with the help of dirty-checking.
            // We have not called the update() for this.
            // Dirty checking
            // - works for only managed entities.
            // - happens before commit()/flush() or before a query execution
            //
            // var empFromDb = em.find(Employee.class, id);
            // Hibernate creates a snapshot of the entity, aka a copy of the original values of the object
            // ex: Employee(id=1, name=Ashish, salary=25000.0)
            // Modify: empFromDb.updateSalary(35000);
            // Since the entity is still managed hibernate detects the difference between original snapshot
            // and current object. This leads to  a dirty object.

            System.out.println("Updating Employee:");
            tx = em.getTransaction();
            tx.begin();
            empFromDb.updateSalary(35000);
            tx.commit();

            id = emp.getId();
            System.out.println("Fetching emp based on id: " + id);
            empFromDb = em.find(Employee.class, id);
            System.out.println("Found employee: " + empFromDb);

            System.out.println("Removing employee:");
            tx = em.getTransaction();
            tx.begin();
            em.remove(empFromDb);
            tx.commit();
            System.out.println("Removed.");

            empFromDb = em.find(Employee.class, id);
            System.out.println("Found employee: " + empFromDb);


        } // Auto close the EntityManager
        JPAUtil.shutdown();
    }
}
