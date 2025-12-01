package org.miniorm.core;

public class SQLGenerator {

    public static String generateInsertSQL(Metadata metaData) {

        var table = metaData.getTableName();
        var columns = metaData.getColumnMap().keySet();

        var columnList = String.join(", ", columns);
        var placeHolders = String.join(", ", columns.stream().map(c -> "?").toList());

        return "INSERT INTO " + table + " (" +columnList + ") VALUES (" + placeHolders + ");";

    }
}
