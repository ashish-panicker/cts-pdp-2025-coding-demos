package org.miniorm;

import org.miniorm.core.EntityManager;
import org.miniorm.core.Metadata;
import org.miniorm.core.SQLGenerator;
import org.miniorm.model.Employee;

public class OrmMain {
    public static void main(String[] args) {
        Employee employee = new Employee(1L, "John Smith", 20000.00);

        Metadata metadata = new Metadata(Employee.class);
//        metadata.getColumnMap().entrySet()
//                .forEach(entry ->
//                        System.out.println(entry.getKey() + ": " + entry.getValue())
//                );
//        System.out.println(metadata.getTableName());

        String insertSQL = SQLGenerator.generateInsertSQL(metadata);
        System.out.println(insertSQL);

        var values = EntityManager.extractValues(employee, metadata);
        System.out.println(values);
    }
}
