package br.com.brenohff.maxipago.MaxiPago.dao;

import br.com.brenohff.maxipago.MaxiPago.entity.CidadeEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadeDAO extends DataBaseDAO {

    public List<CidadeEntity> obterTodasCidades() throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        List<CidadeEntity> cidadesList = new ArrayList<>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM CITY");

            ResultSet queryResult = statement.executeQuery();

            while (queryResult.next()) {
                CidadeEntity cidade = new CidadeEntity();
                cidade.setId(queryResult.getInt("id"));
                cidade.setName(queryResult.getString("name"));
                cidade.setLatitude(queryResult.getDouble("latitude"));
                cidade.setLongitude(queryResult.getDouble("longitude"));

                cidadesList.add(cidade);

            }

            queryResult.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.close();
            }
            if (statement != null) {
                statement.close();
            }
        }

        return cidadesList;

    }
}
