package org.miniorm.core.v2;

public class SQLGeneratorV2 {
    public static String generateInsertSQL(MetadataV2 metaData) {

        var table = metaData.getTableName();
        var columns = metaData.getGetters().keySet();

        // Convert the keys (column names) into a comma-separated list
        var columnList = String.join(", ", columns);

        // Generate a placeholder '?' for each column
        var placeHolders = String.join(", ",
                columns.stream().map(c -> "?").toList()
        );

        return "INSERT INTO " + table +
                " (" + columnList + ") VALUES (" + placeHolders + ");";
    }
}
