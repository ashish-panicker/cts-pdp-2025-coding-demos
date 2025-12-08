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

### JPA Relationship Mappings

**Many To One**

Many Employees One Department. This is one of the most common relationship. Foreign Key is created on the many side.

**One To Many**

One Department Many Employees. Inverse of the previous. Foreign Key is created on the many side.

**One To One**

One Employee One Profile. Generally rare uses Foreign Key or shared Primary Key.

**Many To Many**

Many Employees Many Projects. Usually rare. Uses Join Table.

**Cascade Operations**

Determines what should happen to the related entities when the owning side is persisted or removed.
Different cascade types:
- PERSIST
- MERGE
- REMOVE
- REFRESH
- DETACH
- ALL

**Orphan removal**

This will remove the inverse or child records when the parent records are removed.

**Fetch Stratergy**

When the parent record is fetched will the child records be fetched along with it.
- EAGER [OneToOne, ManyToOne]
- LAZY [OneToMany, ManyToMany]

**Example - Library Management**

- Author: Writes many Books 
- Book: Written by one Author, belongs to one category, has one detailed description
- BookDetail: Has one detail about one book
- Category: Contains multiple books
- Reader: Can borrow many books

