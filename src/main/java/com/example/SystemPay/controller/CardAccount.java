package com.example.SystemPay.controller;


import com.example.SystemPay.entity.Account;
import com.example.SystemPay.entity.Card;
import com.example.SystemPay.service.AccountService;
import com.example.SystemPay.service.CardService;
import com.example.SystemPay.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardAccount {

    @Autowired
    private CardService cardService;


    @GetMapping("/all")
    public ResponseEntity<List<Card>> findAll(){
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> findById(@PathVariable long id){
        return ResponseEntity.ok(cardService.findById(id));
    }

    @PostMapping("/save")
    public HttpStatus save(@RequestBody Card card){
        cardService.insert(card);
        return HttpStatus.CREATED;
    }

    @PatchMapping("/patch")
    public HttpStatus update(@RequestBody Card card){
        cardService.update(card);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id){
        cardService.delete(id);
        return HttpStatus.OK;
    }
}
