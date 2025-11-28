package com.example.generics.dao;

import com.example.generics.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Employee findByName(String name);

}
