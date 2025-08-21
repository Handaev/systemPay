package com.example.SystemPay.entity;

import com.example.SystemPay.entity.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transfer_amount", nullable = false)
    private String amount;

    @Column(name = "type", nullable = false)
    private TransactionType transactionType;

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    @JsonBackReference
    private Card card;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transfer_id")
    private Transfer transfer;
}
