package br.com.brenohff.maxipago.MaxiPago.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseDAO {

    public DataBaseDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Connection getConnection() throws SQLException {
        String dabaseUrl = "jdbc:mysql://localhost:3306/maxipago";
        return DriverManager.getConnection(dabaseUrl, "root", "");
    }

}
