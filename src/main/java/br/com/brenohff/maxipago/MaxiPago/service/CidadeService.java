package br.com.brenohff.maxipago.MaxiPago.service;

import br.com.brenohff.maxipago.MaxiPago.dao.CityDAO;
import br.com.brenohff.maxipago.MaxiPago.entity.CityEntity;
import br.com.brenohff.maxipago.MaxiPago.entity.DistanceEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeService {

    private CityDAO cityDAO = new CityDAO();

    public List<CityEntity> buscarTodasCidades() {
        List<CityEntity> citiesList = new ArrayList<>();

        try {
            citiesList = cityDAO.getAllCities();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return citiesList;
    }

    public List<DistanceEntity> obterCombinacoes(String unidade) {
        List<CityEntity> citiesList = buscarTodasCidades();

        return combineCities(citiesList, new ArrayList<>(), unidade);
    }

    private List<DistanceEntity> combineCities(final List<CityEntity> citiesList, List<DistanceEntity> distList, final String unitType) {
        CityEntity actualCity = citiesList.get(0);

        for (int i = 1; i < citiesList.size(); i++) {
            DistanceEntity distanceEntity = new DistanceEntity();

            distanceEntity.setInitialCity(actualCity.getName());
            distanceEntity.setFinalCity(citiesList.get(i).getName());
            distanceEntity.setUnit(unitType.toUpperCase());
            distanceEntity.setDistance(calculateDistanceBetweenCities(actualCity, citiesList.get(i), unitType));

            distList.add(distanceEntity);
        }

        if (citiesList.subList(1, citiesList.size()).size() != 1) {
            combineCities(citiesList.subList(1, citiesList.size()), distList, unitType);
        }

        return distList;

    }

    private double calculateDistanceBetweenCities(final CityEntity city1, final CityEntity city2, final String unit) {
        double theta = city1.getLongitude() - city2.getLongitude();

        double lat1 = city1.getLatitude();
        double lat2 = city2.getLatitude();

        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));

        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        if (unit.equalsIgnoreCase("KM")) {
            dist = dist * 1.609344;
        }

        return new BigDecimal(dist).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
