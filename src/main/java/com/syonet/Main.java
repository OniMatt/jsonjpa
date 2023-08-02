package com.syonet;

import java.util.ArrayList;
import java.util.List;

import com.syonet.business.CondominioBusiness;
import com.syonet.dao.CondominioDAOImpl;
import com.syonet.domain.Condominio;
import com.syonet.domain.Imovel;

public class Main {
    public static void main( String[] args ) {
        CondominioDAOImpl cdao = new CondominioDAOImpl();
        CondominioBusiness cbusiness = new CondominioBusiness();
        List<Imovel> imoveis1 = new ArrayList<>();
        Condominio c1 = new Condominio("condominio 1", imoveis1);
        Imovel i1 = new Imovel("imovel 1", 1000.50, c1);
        Imovel i2 = new Imovel("imovel 2", 1000.50, c1);
        imoveis1.add(i1);
        imoveis1.add(i2);
        
        List<Imovel> imoveis2 = new ArrayList<>();
        Condominio c2 = new Condominio("condominio 2", imoveis2);
        Imovel i3 = new Imovel("imovel 3", 3000.50, c2);
        Imovel i4 = new Imovel("imovel 4", 4000.50, c2);
        Imovel i5 = new Imovel("imovel 5", 5000.50, c2);
        imoveis2.add(i3);
        imoveis2.add(i4);
        imoveis2.add(i5);

        cdao.create(c1);
        cdao.create(c2);
        
        String json = cbusiness.serializeAllCondominios();

        List< Condominio > condominios = new ArrayList<>();

        condominios = cdao.getAll();
        condominios.stream()
            .forEach(condominio -> cdao.delete(condominio));

        cbusiness.persistCondominiosJson(json);

        System.out.println(cbusiness.serializeMoreThanThreeImoveis());

        List< Condominio > condominiosBaratos = cbusiness.getCondominiosUnderValue(json, 2000);

        condominiosBaratos.stream()
            .forEach( condominio -> System.out.println(condominio.toString()));
        
        
    }
}
