package com.example.SystemPay.repository;


import com.example.SystemPay.entity.Account;
import com.example.SystemPay.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class CardRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Card> findAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Card> criteriaQuery = criteriaBuilder.createQuery(Card.class);
        Root<Card> root = criteriaQuery.from(Card.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Card findById(long accountId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Card> criteriaQuery = criteriaBuilder.createQuery(Card.class);
        Root<Card> root = criteriaQuery.from(Card.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), accountId));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public void insert(Card card){
        entityManager.persist(card);
    }

    public void update(Card card){
        if(entityManager.find(Account.class, card.getId()) != null) {
            entityManager.merge(card);
        }
    }

    public void delete(long id){
        Card card = entityManager.find(Card.class, id);
        if(card != null){
            entityManager.remove(card);
        }
    }
}
