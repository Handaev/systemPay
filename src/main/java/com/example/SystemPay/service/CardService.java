package com.example.SystemPay.service;


import com.example.SystemPay.entity.Card;
import com.example.SystemPay.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    public List<Card> findAll(){
        return cardRepository.findAll();
    }

    public Card findById(long id){
        return cardRepository.findById(id);
    }

    public void insert(Card card){
        cardRepository.insert(card);
    }

    public void update(Card card){
        cardRepository.update(card);
    }

    public void delete(long id){
        cardRepository.delete(id);
    }


}
