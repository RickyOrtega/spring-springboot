package com.bolsadeideas.springboot.app.models.entity.dao;

import com.bolsadeideas.springboot.app.models.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findAll() {
        return em.createQuery("FROM Cliente").getResultList();
    }


    @Override
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        //Buscar con el EntityManager es fácil, y gracias a JPA, el objeto pasa a ser incluído en el contexto de persistencia
        return em.find(Cliente.class, id);
    }


    @Override
    @Transactional
    public void save(Cliente cliente) {

        if(cliente.getId() != null && cliente.getId() > 0){
            em.merge(cliente);
        } else {
            em.persist(cliente);
        }
    }
    @Override
    @Transactional
    public void delete(Long id) {
        Cliente cliente = findOne(id);
        em.remove(cliente);
    }
}