package com.example.SystemPay.dto;

import lombok.Data;

@Data
public class AccountDtoResponse {
    private long id;

    private String email;

    private String password;

    private String phone;
}
