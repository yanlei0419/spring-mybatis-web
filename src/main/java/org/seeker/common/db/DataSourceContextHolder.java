package org.seeker.common.db;

public class DataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
    private static String defaultDB;

    public static void setDBType(String dbType) {

        if ("".equals(dbType)) {

        }
        contextHolder.set(dbType);
    }

    public static String getDBType() {
        return ((String) contextHolder.get());
    }

    public static void clearDBType() {
        contextHolder.remove();
    }
}  