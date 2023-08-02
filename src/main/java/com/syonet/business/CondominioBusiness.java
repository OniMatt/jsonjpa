package com.syonet.business;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.syonet.dao.CondominioDAOImpl;
import com.syonet.domain.Condominio;

public class CondominioBusiness {
    CondominioDAOImpl cdao = new CondominioDAOImpl();
    ObjectMapper objectMapper = new ObjectMapper();

    public String serializeAllCondominios( ) {
        List<Condominio> condominios = cdao.getAll();
        return serializeCondominioList(condominios);
    }

    private String serializeCondominioList(List<Condominio> condominios) {
        try {
            return objectMapper.writeValueAsString( condominios );
        } catch ( Exception e ) {
            System.out.println( e );
            return "{}";
        }
    }

    public String serializeMoreThanThreeImoveis( ) {
        List< Condominio > condominios = cdao.getAll();
        
        condominios = condominios.stream()
            .filter(condominio -> condominio.getImoveis().size() >= 3)
            .collect(Collectors.toList());

        return serializeCondominioList(condominios);
    }

    public List< Condominio > getCondominiosUnderValue( String json, double valor ) {
        List< Condominio > condominios = new ArrayList<>();
        try {
            condominios = objectMapper.readValue( json, new TypeReference<List<Condominio>>() {} );
        } catch ( Exception e ) {
            System.out.println( "Não foi possível retornar objetos a partir do JSON.");
            System.out.println( e );
        }

        condominios = condominios.stream()
            .filter( condominio -> condominio.getImoveis()
                .stream()
                .anyMatch( imovel -> imovel.getValorAluguel() < valor))
            .collect(Collectors.toList());

        return condominios;
    }

    public void persistCondominiosJson( String json ){
        List< Condominio > condominios = new ArrayList<>();
        try {
            condominios = objectMapper.readValue( json, new TypeReference<List<Condominio>>() {} );
        } catch ( Exception e ) {
            System.out.println( "Não foi possível retornar objetos a partir do JSON.");
            System.out.println( e );
            return;
        }

        condominios.stream()
            .forEach(condominio -> {
                cdao.update(condominio);
            });
    }
}
