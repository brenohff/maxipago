package br.com.brenohff.maxipago.MaxiPago.controller;

import br.com.brenohff.maxipago.MaxiPago.entity.CityEntity;
import br.com.brenohff.maxipago.MaxiPago.entity.DistanceEntity;
import br.com.brenohff.maxipago.MaxiPago.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cidade")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping(value = "buscarTodasCidades", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<CityEntity> buscarTodasCidades() {
        return cidadeService.buscarTodasCidades();
    }

    @GetMapping(value = "obterCombinacoes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity obterCombinacoes(@RequestParam("tipoRetorno") String tipoRetorno, @RequestParam("unidade") String unidade) {
        HttpHeaders httpHeaders = new HttpHeaders();

        if (tipoRetorno.equalsIgnoreCase("json")) {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        } else {
            httpHeaders.setContentType(MediaType.APPLICATION_XML);
        }

        List<DistanceEntity> distanceList = cidadeService.obterCombinacoes(unidade);

        return ResponseEntity.status(HttpStatus.OK).headers(httpHeaders).body(distanceList);
    }

}