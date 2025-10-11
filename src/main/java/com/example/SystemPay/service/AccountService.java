package com.example.SystemPay.service;

import com.example.SystemPay.dto.AccountDtoRequest;
import com.example.SystemPay.dto.AccountDtoResponse;
import com.example.SystemPay.entity.Account;
import com.example.SystemPay.mapper.AccountDtoMapper;
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

    @Autowired
    AccountDtoMapper accountDtoMapper;

    public List<AccountDtoResponse> findAll(){
        return accountRepository.findAll().stream().
                map(x -> accountDtoMapper.accountToAccountDtoResponse(x)).toList();
    }

    public AccountDtoResponse findById(long id){
        return accountDtoMapper.accountToAccountDtoResponse(accountRepository.findById(id));
    }

    public AccountDtoResponse insert(AccountDtoRequest accountDtoRequest) throws ExecutionException, InterruptedException {
        Account account = accountRepository.insert(accountDtoMapper.AccountDtoRequestToAccount(accountDtoRequest));
        AccountDtoResponse responseAccount = accountDtoMapper.accountToAccountDtoResponse(account);

        SendResult<String, Account> result =
                kafkaTemplate.send("created-topic", account.getEmail(), account).get();

        return responseAccount;
    }

    public AccountDtoResponse update(Account account){
        return accountDtoMapper.accountToAccountDtoResponse(accountRepository.update(account));
    }

    public AccountDtoResponse delete(long id){
        return accountDtoMapper.accountToAccountDtoResponse(accountRepository.delete(id));
    }

}
