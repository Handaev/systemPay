package com.example.SystemPay.service;

import com.example.SystemPay.dto.AccountDto;
import com.example.SystemPay.entity.Account;
import com.example.SystemPay.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.*;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private KafkaTemplate<String, Account> kafkaTemplate;

    public List<Account> findAll(){
        return accountRepository.findAll();
    }

    public Account findById(long id){
        return accountRepository.findById(id);
    }

    public void insert(AccountDto accountDto){
        Account account = new Account(
                accountDto.getId(),
                accountDto.getEmail(),
                accountDto.getPassword(),
                accountDto.getPhone()
        );
        accountRepository.insert(account);

        CompletableFuture<SendResult<String, Account>> future = kafkaTemplate
                .send("created-topic", accountDto.getEmail(), account);

        future.whenComplete((result, exception) -> {
            if(exception != null){
                System.out.println("Failed to send message: " + exception.getMessage());
            }else {
                System.out.println("Result sent successfully: " + result.getRecordMetadata().toString());
            }
        });
        System.out.println("ID Account: " + account.getId());
    }

    public void update(Account account){
        accountRepository.update(account);
    }

    public void delete(long id){
        accountRepository.delete(id);
    }

}
