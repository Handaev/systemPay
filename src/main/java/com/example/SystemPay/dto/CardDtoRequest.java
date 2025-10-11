package com.example.SystemPay.dto;

import com.example.SystemPay.entity.Account;
import lombok.Data;

import java.util.Date;

@Data
public class CardDtoRequest {
    private Long id;

    private String number;

    private String email;

    private String password;

    private Date expiryDate;

    private String cvvEncrypted;

    private Account account;
}
