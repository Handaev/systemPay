package com.example.SystemPay.controller;


import com.example.SystemPay.entity.Account;
import com.example.SystemPay.service.AccountService;
import com.example.SystemPay.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private KafkaService kafkaService;

    @GetMapping("/all")
    public ResponseEntity<List<Account>> findAll(){
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> findById(@PathVariable long id){
        return ResponseEntity.ok(accountService.findById(id));
    }

    @PostMapping("/save")
    public HttpStatus save(@RequestBody Account account){
        accountService.insert(account);
        return HttpStatus.CREATED;
    }

    @PatchMapping("/patch")
    public HttpStatus update(@RequestBody Account account){
        accountService.update(account);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id){
        accountService.delete(id);
        return HttpStatus.OK;
    }


    @GetMapping
    public HttpStatus send(){
        kafkaService.sendMessage();
        return HttpStatus.OK;
    }

    @GetMapping("/some-endpoint")
    public ResponseEntity<String> someMethod() {
        kafkaService.sendMessage();
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

    @GetMapping("/read")
    public ResponseEntity<String> read() {
        kafkaService.readMessages();
        return ResponseEntity.status(HttpStatus.OK).body("Success");
    }

}
