package br.com.brenohff.maxipago.MaxiPago.dao;

import br.com.brenohff.maxipago.MaxiPago.entity.CityEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO extends DataBaseDAO {

    public List<CityEntity> getAllCities() throws SQLException {

        Connection connection = null;
        PreparedStatement statement = null;
        List<CityEntity> citiesList = new ArrayList<>();

        try {
            connection = getConnection();
            statement = connection.prepareStatement("SELECT * FROM CITY");

            ResultSet queryResult = statement.executeQuery();

            while (queryResult.next()) {
                CityEntity city = new CityEntity();
                city.setId(queryResult.getInt("id"));
                city.setName(queryResult.getString("name"));
                city.setLatitude(queryResult.getDouble("latitude"));
                city.setLongitude(queryResult.getDouble("longitude"));

                citiesList.add(city);

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

        return citiesList;

    }
}
