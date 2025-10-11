package com.example.SystemPay.dto;

import com.example.SystemPay.entity.Card;
import lombok.Data;

@Data
public class TransferDtoRequest {

    private Card cardFrom;
    private Card cardTo;
}
