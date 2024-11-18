package org.swing.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class BaseDAO<T> {
    private static final String TEST_DB_URL = "jdbc:mysql://localhost:3306/banco_malvader_test";
    private static final String PROD_DB_URL = "jdbc:mysql://localhost:3306/banco_lab_final";
    private static final String USER = "root"; // Altere conforme necessário
    private static final String PASSWORD = "$Ony995647"; // Altere conforme necessário

    private static boolean isTestMode = false;

    public static void setTestMode(boolean testMode) {
        isTestMode = testMode;
    }

    protected Connection getConnection() throws SQLException {
        String url = isTestMode ? TEST_DB_URL : PROD_DB_URL;
        return DriverManager.getConnection(url, USER, PASSWORD);
    }

    public abstract T findById(int id) throws SQLException;
}
