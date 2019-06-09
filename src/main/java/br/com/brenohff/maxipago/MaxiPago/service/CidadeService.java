package br.com.brenohff.maxipago.MaxiPago.service;

import br.com.brenohff.maxipago.MaxiPago.dao.CidadeDAO;
import br.com.brenohff.maxipago.MaxiPago.entity.CidadeEntity;
import br.com.brenohff.maxipago.MaxiPago.entity.DistanciaEntity;
import br.com.brenohff.maxipago.MaxiPago.exception.CidadeNaoEncontrada;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CidadeService {

    private CidadeDAO cidadeDAO = new CidadeDAO();

    /**
     * Obtem todas as cidades cadastradas no banco de dados
     *
     * @return Retornará uma lista de cidades
     */
    public List<CidadeEntity> buscarTodasCidades() {
        List<CidadeEntity> citiesList = new ArrayList<>();

        try {
            citiesList = cidadeDAO.obterTodasCidades();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return citiesList;
    }

    /**
     * Método utilizado para obter as combinações entre as cidades
     *
     * @param unidade Unidade de medida (MI ou KM)
     * @return Retornará uma lista de distancias combinadas
     */
    public List<DistanciaEntity> obterCombinacoes(String unidade) {
        List<CidadeEntity> cidadesList = buscarTodasCidades();

        if (cidadesList.size() < 3) {
            throw new CidadeNaoEncontrada("É necessario ao menos 3 cidades cadastradas para fazer combinação");
        }

        return combinarCidades(cidadesList, new ArrayList<>(), unidade);
    }

    /**
     * Método para realizar as combinações das cidades utilizando recursividade.
     *
     * @param citiesList Lista inicial das cidades
     * @param distList   Lista de distâncias já iniciada
     * @param unitType   Unidade de medida (MI ou KM)
     * @return Irá retornar uma lista de distâncias
     */
    private List<DistanciaEntity> combinarCidades(final List<CidadeEntity> citiesList, List<DistanciaEntity> distList, final String unitType) {
        CidadeEntity actualCity = citiesList.get(0);

        for (int i = 1; i < citiesList.size(); i++) {
            DistanciaEntity distanciaEntity = new DistanciaEntity();

            distanciaEntity.setInitialCity(actualCity.getName());
            distanciaEntity.setFinalCity(citiesList.get(i).getName());
            distanciaEntity.setUnit(unitType.toUpperCase());
            distanciaEntity.setDistance(calculateDistanceBetweenCities(actualCity, citiesList.get(i), unitType));

            distList.add(distanciaEntity);
        }

        //Se a sublista tiver tamanho 1, ela não precisa mais ser combinada.
        if (citiesList.subList(1, citiesList.size()).size() != 1) {
            combinarCidades(citiesList.subList(1, citiesList.size()), distList, unitType);
        }

        return distList;

    }

    /**
     * O método utilizado abaixo foi retirado do site https://www.geodatasource.com/developers/java pois o mesmo já é utilizado
     * em todos os produtos da empresa GEODATASOURCE, logo é confiavel e de fácil implementação
     *
     * @param cidade1 Cidade 1
     * @param cidade2 Cidade 2
     * @param unidade Unidade de medida (MI ou KM)
     * @return Irá retornar um double com a distância entre as 2 cidades
     */
    private double calculateDistanceBetweenCities(final CidadeEntity cidade1, final CidadeEntity cidade2, final String unidade) {
        double theta = cidade1.getLongitude() - cidade2.getLongitude();

        double lat1 = cidade1.getLatitude();
        double lat2 = cidade2.getLatitude();

        double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2))
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));

        dist = Math.acos(dist);
        dist = Math.toDegrees(dist);
        dist = dist * 60 * 1.1515;

        if (unidade.equalsIgnoreCase("KM")) {
            dist = dist * 1.609344;
        }

        return new BigDecimal(dist).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

}
