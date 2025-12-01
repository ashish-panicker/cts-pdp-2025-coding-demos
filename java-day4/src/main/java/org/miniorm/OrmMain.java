package org.miniorm;

import org.miniorm.core.EntityManager;
import org.miniorm.core.Metadata;
import org.miniorm.core.SQLGenerator;
import org.miniorm.core.v2.EntityManagerV2;
import org.miniorm.core.v2.MetadataV2;
import org.miniorm.core.v2.SQLGeneratorV2;
import org.miniorm.model.Employee;

public class OrmMain {
    public static void main(String[] args) throws IllegalAccessException {
        Employee employee = new Employee(1L, "John Smith", 20000.00);

        Metadata metadata = new Metadata(Employee.class);
        MetadataV2 metadataV2 = new MetadataV2(Employee.class);

//        metadata.getColumnMap().entrySet()
//                .forEach(entry ->
//                        System.out.println(entry.getKey() + ": " + entry.getValue())
//                );
//        System.out.println(metadata.getTableName());

        String insertSQL = SQLGenerator.generateInsertSQL(metadata);
        System.out.println(insertSQL);

        var values = EntityManager.extractValues(employee, metadata);
        System.out.println(values);

        System.out.println("metadataV2");
        String insertSQL2 = SQLGeneratorV2.generateInsertSQL(metadataV2);
        var values2 = EntityManagerV2.extractValues(employee, metadataV2);
        System.out.println(values2);
        System.out.println(insertSQL2);
    }
}
