package com.example.SystemPay.controller;

import com.example.SystemPay.dto.AccountDtoRequest;
import com.example.SystemPay.dto.AccountDtoResponse;
import com.example.SystemPay.entity.Account;
import com.example.SystemPay.service.AccountService;
//import com.example.SystemPay.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/all")
    public ResponseEntity<List<AccountDtoResponse>> findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDtoResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<AccountDtoResponse> save(@RequestBody AccountDtoRequest accountDtoRequest) throws ExecutionException, InterruptedException {
        AccountDtoResponse responseAccount = accountService.insert(accountDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseAccount);
    }

    @PatchMapping("/patch")
    public ResponseEntity<AccountDtoResponse> update(@RequestBody Account account){
        AccountDtoResponse responseAccount = accountService.update(account);
        return ResponseEntity.status(HttpStatus.OK).body(responseAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccountDtoResponse> delete(@PathVariable long id){
        AccountDtoResponse responseAccount = accountService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseAccount);
    }
}
