package com.example.SystemPay.service;

import com.example.SystemPay.entity.Card;
import com.example.SystemPay.entity.Transaction;
import com.example.SystemPay.repository.CardRepository;
import com.example.SystemPay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public List<Transaction> findAll(){
        return transactionRepository.findAll();
    }

    public Transaction findById(long id){
        return transactionRepository.findById(id);
    }

    public void insert(Transaction transaction){
        transactionRepository.insert(transaction);
    }

    public void update(Transaction transaction){
        transactionRepository.update(transaction);
    }

    public void delete(long id){
        transactionRepository.delete(id);
    }

}
