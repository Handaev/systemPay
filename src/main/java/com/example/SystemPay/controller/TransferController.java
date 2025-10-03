package com.example.SystemPay.controller;

import com.example.SystemPay.entity.Card;
import com.example.SystemPay.entity.Transfer;
import com.example.SystemPay.service.CardService;
import com.example.SystemPay.service.KafkaService;
import com.example.SystemPay.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transfer")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @GetMapping("/all")
    public ResponseEntity<List<Transfer>> findAll(){
        return ResponseEntity.ok(transferService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transfer> findById(@PathVariable long id){
        return ResponseEntity.ok(transferService.findById(id));
    }

    @PostMapping("/save")
    public HttpStatus save(@RequestBody Transfer transfer){
        transferService.insert(transfer);
        return HttpStatus.CREATED;
    }

    @PatchMapping("/patch")
    public HttpStatus update(@RequestBody Transfer transfer){
        transferService.update(transfer);
        return HttpStatus.OK;
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable long id){
        transferService.delete(id);
        return HttpStatus.OK;
    }
}
