package com.example.SystemPay.mapper;

import com.example.SystemPay.dto.CardDtoRequest;
import com.example.SystemPay.dto.CardDtoResponse;
import com.example.SystemPay.entity.Card;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CardDtoMapper {
    Card cardDtoRequestToCard(CardDtoRequest cardDtoRequest);
    CardDtoResponse cardToCardDtoResponse(Card card);
}
