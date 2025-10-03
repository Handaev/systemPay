package com.example.SystemPay.service;

import com.example.SystemPay.entity.Card;
import com.example.SystemPay.entity.Transaction;
import com.example.SystemPay.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionService transactionService;

    public List<Transaction> findAll(){
        return transactionService.findAll();
    }

    public Transaction findById(long id){
        return transactionService.findById(id);
    }

    public void insert(Transaction transaction){
        transactionService.insert(transaction);
    }

    public void update(Transaction transaction){
        transactionService.update(transaction);
    }

    public void delete(long id){
        transactionService.delete(id);
    }

}
