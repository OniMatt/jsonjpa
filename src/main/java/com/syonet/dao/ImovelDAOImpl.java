package com.syonet.dao;

import java.util.List;

import com.syonet.domain.Imovel;
import com.syonet.generic.GenericDAO;

public class ImovelDAOImpl implements GenericDAO< Imovel > {

    @Override
    public void create( Imovel imovel ) {
        transaction.begin();
        try {
            em.persist( imovel );
            transaction.commit();
        } catch ( Exception e ) {
            System.out.println( e );
            transaction.rollback();
        }
    }

    @Override
    public Imovel getById( Integer id ) {
        return em.createQuery( "SELECT i FROM Imovel i WHERE id = " + id, Imovel.class )
            .getSingleResult();
    }

    @Override
    public List< Imovel > getAll() {
        return em.createQuery( "SELECT i FROM Imovel i", Imovel.class )
            .getResultList();
    }

    @Override
    public void update( Imovel imovel ) {
        transaction.begin();
        try {
            em.merge( imovel );
            transaction.commit();
        } catch ( Exception e ) {
            System.out.println( e );
            transaction.rollback();
        }
    }

    @Override
    public void delete( Imovel imovel ) {
        transaction.begin();
        try {
            em.remove( imovel );
            transaction.commit();
        } catch ( Exception e ) {
            System.out.println( e );
            transaction.rollback();
        }
    }
    
}
