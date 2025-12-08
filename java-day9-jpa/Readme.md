# Working with JPA

### JPA

**Java Persistence API**, is a specification, not an implementation. I defines APIs for **Object Relational Mapping** and
relational databases.

#### **JPA Layers**
- Application code
- JPA API
- JPA implementation/Provider [Hibernate]
- JDBC + Database

### ORM

**Object Relational Mapping**, a programming technique that allows us to interact with relational structures like 
tables, rows and columns using classes, objects and methods. 

ORM maps
- Tables to classes
- Rows to objects
- Column to Field
- Foreign Key to Relation


### JPA ORM Annotations

- `@Entity` - Simple POJO classes that represents a table in the database.
- `@Table` - Names the database table which is mapped with the POJO.
- `@Id` - Marks the primary key attribute of the entity class.

### JPA Rules

- Classes must have `@Entity` annotation.
- Must have a zero argument constructor.
- Must have a primary key attribute marked used `@Id`.
- Entity classes must not be final classes.
- Entity attributes must not be final attributes.

### Persistence Context and Entity Lifecycle

#### **Entity States**

- New/Transient
- Managed/Persistent
- Detached
- Removed