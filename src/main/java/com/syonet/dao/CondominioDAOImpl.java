package com.syonet.dao;

import java.util.List;

import com.syonet.domain.Condominio;
import com.syonet.generic.GenericDAO;

public class CondominioDAOImpl implements GenericDAO< Condominio > {

    @Override
    public void create( Condominio condominio ) {
        transaction.begin();
        try {
            em.persist( condominio );
            transaction.commit();
        } catch ( Exception e ) {
            System.out.println( e );
            transaction.rollback();
        }
    }

    @Override
    public Condominio getById( Integer id ) {
        return em.createQuery( "SELECT c FROM Condominio c WHERE id = " + id, Condominio.class )
            .getSingleResult();
    }

    @Override
    public List< Condominio > getAll() {
        return em.createQuery( "SELECT c FROM Condominio c", Condominio.class )
            .getResultList();
    }

    @Override
    public void update( Condominio condominio ) {
        transaction.begin();
        try {
            em.merge( condominio );
            transaction.commit();
        } catch ( Exception e ) {
            System.out.println( e );
            transaction.rollback();
        }
    }

    @Override
    public void delete( Condominio condominio ) {
        transaction.begin();
        try {
            em.remove( condominio );
            transaction.commit();
        } catch ( Exception e ) {
            System.out.println( e );
            transaction.rollback();
        }
    }
    
}
