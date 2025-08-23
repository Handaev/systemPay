package com.example.SystemPay.repository;

import com.example.SystemPay.entity.Transfer;
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
public class TransferRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Transfer> findAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transfer> criteriaQuery = criteriaBuilder.createQuery(Transfer.class);
        Root<Transfer> root = criteriaQuery.from(Transfer.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Transfer findById(long transferId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Transfer> criteriaQuery = criteriaBuilder.createQuery(Transfer.class);
        Root<Transfer> root = criteriaQuery.from(Transfer.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), transferId));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public void insert(Transfer transfer){
        entityManager.persist(transfer);
    }

    public void update(Transfer transfer){
        if(entityManager.find(Transfer.class, transfer.getId()) != null) {
            entityManager.merge(transfer);
        }
    }

    public void delete(long id){
        Transfer transfer = entityManager.find(Transfer.class, id);
        if(transfer != null){
            entityManager.remove(transfer);
        }
    }
}