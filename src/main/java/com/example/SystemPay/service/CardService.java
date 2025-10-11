package com.example.SystemPay.service;

import com.example.SystemPay.entity.Card;
import com.example.SystemPay.dto.CardDtoRequest;
import com.example.SystemPay.dto.CardDtoResponse;
import com.example.SystemPay.mapper.CardDtoMapper;
import com.example.SystemPay.repository.CardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private KafkaTemplate<String, Card> kafkaTemplate;

    @Autowired
    private CardDtoMapper cardDtoMapper;

    public List<CardDtoResponse> findAll() {
        return cardRepository.findAll().stream()
                .map(cardDtoMapper::cardToCardDtoResponse)
                .toList();
    }

    public CardDtoResponse findById(long id) {
        return cardDtoMapper.cardToCardDtoResponse(cardRepository.findById(id));
    }

    public CardDtoResponse insert(CardDtoRequest cardDtoRequest) {
        Card card = cardRepository.insert(cardDtoMapper.cardDtoRequestToCard(cardDtoRequest));
        return cardDtoMapper.cardToCardDtoResponse(card);
    }

    public CardDtoResponse update(CardDtoRequest cardDtoRequest) throws EntityNotFoundException{
        Card updatedCard = cardRepository.update(cardDtoMapper.cardDtoRequestToCard(cardDtoRequest));
        return cardDtoMapper.cardToCardDtoResponse(updatedCard);
    }

    public CardDtoResponse delete(long id) throws EntityNotFoundException{
        Card deletedCard = cardRepository.delete(id);
        return cardDtoMapper.cardToCardDtoResponse(deletedCard);
    }
}