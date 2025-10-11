package com.example.SystemPay.controller;


import com.example.SystemPay.dto.CardDtoRequest;
import com.example.SystemPay.dto.CardDtoResponse;
import com.example.SystemPay.entity.Card;
import com.example.SystemPay.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    @GetMapping("/all")
    public ResponseEntity<List<CardDtoResponse>> findAll(){
        return ResponseEntity.ok(cardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardDtoResponse> findById(@PathVariable long id){
        return ResponseEntity.ok(cardService.findById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<CardDtoResponse> save(@RequestBody CardDtoRequest cardDtoRequest) {
        CardDtoResponse cardDtoResponse = cardService.insert(cardDtoRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(cardDtoResponse);
    }

    @PatchMapping("/patch")
    public ResponseEntity<CardDtoResponse> update(@RequestBody CardDtoRequest cardDtoRequest){
        CardDtoResponse cardDtoResponse = cardService.update(cardDtoRequest);
        return ResponseEntity.status(HttpStatus.OK).body(cardDtoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardDtoResponse> delete(@PathVariable long id){
        CardDtoResponse cardDtoResponse = cardService.delete(id);;
        return ResponseEntity.status(HttpStatus.OK).body(cardDtoResponse);
    }
}
