package com.example.SystemPay.dto;

import com.example.SystemPay.entity.enums.Status;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class AccountDtoRequest {

    private String email;

    private String password;

    private String phone;
}
