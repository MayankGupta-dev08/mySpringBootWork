package dev.mayank.infinityschoolhouse.util;

public final class SQLQueryBuilder {

    public static String buildInsertQuery(String tableName, String... columns) {
        StringBuilder query = new StringBuilder("INSERT INTO ");
        query.append(tableName).append(" (");
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]);
            if (i < columns.length - 1)
                query.append(", ");
        }
        query.append(") VALUES (");
        for (int i = 0; i < columns.length; i++) {
            query.append("?");
            if (i < columns.length - 1)
                query.append(", ");
        }
        query.append(")");
        return query.toString();
    }

    public static String buildUpdateQuery(String tableName, String idColumn, String... columns) {
        StringBuilder query = new StringBuilder("UPDATE ");
        query.append(tableName).append(" SET ");
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]).append(" = ?");
            if (i < columns.length - 1)
                query.append(", ");
        }
        query.append(" WHERE ").append(idColumn).append(" = ?");
        return query.toString();
    }

    public static String buildDeleteQuery(String tableName, String idColumn) {
        return "DELETE FROM " + tableName + " WHERE " + idColumn + " = ?";
    }

    public static String buildSelectQuery(String tableName, String... columns) {
        StringBuilder query = new StringBuilder("SELECT ");
        for (int i = 0; i < columns.length; i++) {
            query.append(columns[i]);
            if (i < columns.length - 1)
                query.append(", ");
        }
        query.append(" FROM ").append(tableName);
        return query.toString();
    }

    public static String buildSelectQueryWithCondition(String tableName, String conditionColumn, String... columns) {
        StringBuilder query = new StringBuilder(buildSelectQuery(tableName, columns));
        query.append(" WHERE ").append(conditionColumn).append(" = ?");
        return query.toString();
    }
}