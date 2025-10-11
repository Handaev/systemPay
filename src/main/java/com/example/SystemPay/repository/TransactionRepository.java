package com.example.SystemPay.repository;


import com.example.SystemPay.entity.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TransactionRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Transaction> findAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Transaction findById(long transactionId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transaction> criteriaQuery = criteriaBuilder.createQuery(Transaction.class);
        Root<Transaction> root = criteriaQuery.from(Transaction.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), transactionId));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public Transaction insert(Transaction transaction){
        entityManager.persist(transaction);
        return transaction;
    }

    public Transaction update(Transaction transaction){
        if(entityManager.find(Transaction.class, transaction.getId()) != null) {
            entityManager.merge(transaction);
            return transaction;
        }
        throw new EntityNotFoundException("Card not found with id: " + transaction.getId());
    }

    public Transaction delete(long id){
        Transaction transaction = entityManager.find(Transaction.class, id);
        if(transaction != null){
            entityManager.remove(transaction);
            return transaction;
        }
        throw new EntityNotFoundException("Card not found with id: " + id);
    }

}
