package com.example.SystemPay.service;

import com.example.SystemPay.entity.Account;
import com.example.SystemPay.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findById(long id){
        return accountRepository.findById(id);
    }

    public void insert(Account account){
        accountRepository.insert(account);
    }

    public void update(Account account){
        accountRepository.update(account);
    }

    public void delete(long id){
        accountRepository.delete(id);
    }

}
