package com.example.SystemPay.controller;

import com.example.SystemPay.dto.TransactionDtoRequest;
import com.example.SystemPay.dto.TransactionDtoResponse;
import com.example.SystemPay.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDtoResponse>> findAll(){
        return ResponseEntity.ok(transactionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDtoResponse> findById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(transactionService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<TransactionDtoResponse> save(@RequestBody TransactionDtoRequest transactionDtoRequest)
            throws ExecutionException, InterruptedException{
        TransactionDtoResponse transactionDtoResponse =  transactionService.insert(transactionDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDtoResponse);
    }

    @PatchMapping("/patch")
    public ResponseEntity<TransactionDtoResponse> update(@RequestBody TransactionDtoRequest transactionDtoRequest){
        TransactionDtoResponse transactionDtoResponse = transactionService.update(transactionDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(transactionDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransactionDtoResponse> delete(@PathVariable long id){
        TransactionDtoResponse transactionDtoResponse = transactionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(transactionDtoResponse);
    }
}
