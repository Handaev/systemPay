package com.example.SystemPay.dto;

import com.example.SystemPay.entity.Card;
import com.example.SystemPay.entity.Transfer;
import com.example.SystemPay.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Data
public class TransactionDtoRequest {

    private String amount;

    private TransactionType transactionType;

    private Date createdAt;

    private Card card;

    private Transfer transfer;
}
