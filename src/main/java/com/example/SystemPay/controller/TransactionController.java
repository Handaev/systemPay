package com.example.SystemPay.controller;

import com.example.SystemPay.entity.Transaction;
import com.example.SystemPay.entity.Transfer;
import com.example.SystemPay.service.TransactionService;
import com.example.SystemPay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> findAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable long id){
        return ResponseEntity.ok(transactionService.findById(id));
    }

    @PostMapping("/save")
    public HttpStatus save(@RequestBody Transaction transaction){
        transactionService.insert(transaction);
        return HttpStatus.CREATED;
    }

    @PatchMapping("/patch")
    public HttpStatus update(@RequestBody Transaction transaction){
        transactionService.update(transaction);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id){
        transactionService.delete(id);
        return HttpStatus.OK;
    }
}
