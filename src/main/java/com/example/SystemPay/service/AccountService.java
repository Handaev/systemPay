package com.example.SystemPay.service;

import com.example.SystemPay.entity.Account;
import com.example.SystemPay.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

}
