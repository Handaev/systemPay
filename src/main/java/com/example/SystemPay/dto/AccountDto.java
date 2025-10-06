package com.example.SystemPay.dto;

import com.example.SystemPay.entity.enums.Status;
import jakarta.persistence.Column;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class AccountDto {

    private Long id;

    private String email;

    private String password;

    private String phone;

    private Date createdAt;

    private Status status = Status.ACTIVE;



}
