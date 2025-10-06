package com.example.SystemPay.repository;

import com.example.SystemPay.entity.Account;
import com.example.SystemPay.entity.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AccountRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Mock
    CriteriaBuilder criteriaBuilder;

    @Mock
    CriteriaQuery<Account> criteriaQuery;

    @Mock
    Root<Account> root;

    @Mock
    private TypedQuery<Account> typedQuery;

    @InjectMocks
    AccountRepository accountRepository;



    @Test
    @DisplayName("update element")
    public void update(){
        Timestamp specificTimestamp = Timestamp.valueOf("2025-08-24 18:15:04.940801");

        Account expectedResult = new Account();
        expectedResult.setId(1L);
        expectedResult.setEmail("dima.petrov@example.com");
        expectedResult.setPassword("$2a$10$rD6J5K8sL9mT2vX1wY3ZxO");
        expectedResult.setPhone("79161434567");
        expectedResult.setCreatedAt(specificTimestamp);
        expectedResult.setStatus(Status.ACTIVE);

        accountRepository.update(expectedResult);

        Account actualResult = accountRepository.findById( 1L);

        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("save element")
    public void insert(){ //тест ущербный, переделать
        Timestamp specificTimestamp = Timestamp.valueOf("2025-08-24 18:15:04.940801");

        Account expectedResult = new Account();
        expectedResult.setId(2L);
        expectedResult.setEmail("ivan.petrov@example.com");
        expectedResult.setPassword("$2a$10$rD6J5K8sL9mT2vX1wY3ZxO");
        expectedResult.setPhone("79161434567");
        expectedResult.setCreatedAt(specificTimestamp);
        expectedResult.setStatus(Status.ACTIVE);

        accountRepository.insert(expectedResult);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Account.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Account.class)).thenReturn(root);

        Path<Object> idPath = mock(Path.class);
        when(root.get("id")).thenReturn(idPath);

        Predicate idPredicate = mock(Predicate.class);
        when(criteriaBuilder.equal(idPath, 2L)).thenReturn(idPredicate);

        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(expectedResult);

        Account actualResult = accountRepository.findById(expectedResult.getId());

        assertThat(actualResult).isEqualTo(expectedResult);
    }



    @Test
    @DisplayName("Search by Id")
    public void findById(){
        Timestamp specificTimestamp = Timestamp.valueOf("2025-08-24 18:15:04.940801");

        Account expectedResult = new Account();
        expectedResult.setEmail("ivan.petrov@example.com");
        expectedResult.setPassword("$2a$10$rD6J5K8sL9mT2vX1wY3ZxO");
        expectedResult.setFirstName("Иван");
        expectedResult.setLastName("Петров");
        expectedResult.setMiddleName("Сергеевич");
        expectedResult.setPhone("79161234567");
        expectedResult.setCreatedAt(specificTimestamp);
        expectedResult.setStatus(Status.ACTIVE);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Account.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Account.class)).thenReturn(root);

        Path<Object> idPath = mock(Path.class);
        when(root.get("id")).thenReturn(idPath);

        Predicate idPredicate = mock(Predicate.class);
        when(criteriaBuilder.equal(idPath, 1L)).thenReturn(idPredicate);

        when(entityManager.createQuery(criteriaQuery)).thenReturn(typedQuery);
        when(typedQuery.getSingleResult()).thenReturn(expectedResult);

        Account actualResult = accountRepository.findById(1L);

        assertThat(actualResult).isEqualTo(expectedResult);

        Mockito.verify(entityManager, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Account.class);
        Mockito.verify(criteriaQuery, times(1)).from(Account.class);
        verify(entityManager, times(1)).createQuery(criteriaQuery);
        verify(typedQuery, times(1)).getSingleResult();
    }





}
