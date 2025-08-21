package com.example.SystemPay.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "transfers")
@NoArgsConstructor
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_from_id", nullable = false)
    @JsonBackReference
    private Card cardFrom;

    @ManyToOne
    @JoinColumn(name = "card_to_id", nullable = false)
    @JsonBackReference
    private Card cardTo;
}
