package com.example.SystemPay.repository;

import com.example.SystemPay.entity.Account;
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
public class AccountRepository {

    @Autowired
    private EntityManager entityManager;

    public List<Account> findAll(){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        criteriaQuery.select(root);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    public Account findById(long accountId){
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Account> criteriaQuery = criteriaBuilder.createQuery(Account.class);
        Root<Account> root = criteriaQuery.from(Account.class);
        criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("id"), accountId));

        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }

    public void insert(Account account){
        entityManager.persist(account);
    }

    public void update(Account account){
        if(entityManager.find(Account.class, account.getId()) != null) {
            entityManager.merge(account);
        }
    }

    public void delete(long id){
        Account account = entityManager.find(Account.class, id);
        if(account != null){
            entityManager.remove(account);
        }
    }
}
