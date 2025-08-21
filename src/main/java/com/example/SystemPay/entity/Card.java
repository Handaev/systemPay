package com.example.SystemPay.entity;


import com.example.SystemPay.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_masked", nullable = false, unique = true)
    private String number;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @Column(name = "expiry_date", nullable = false)
    private Date expiryDate;

    @Column(name = "cvv_encrypted", nullable = false)
    private String cvvEncrypted;

    @Column(name = "status", nullable = false)
    private Status status = Status.ACTIVE;

    @Column(name = "balance")
    private String balance = "0";

    @Column(name = "created_at", nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Transaction> transaction;

    @OneToMany(mappedBy = "cardFrom", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Transfer> outgoingTransfers;

    @OneToMany(mappedBy = "cardTo", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Collection<Transfer> incomingTransfers;

    @PrePersist
    private void expiryDatePlusSixYear() {
        if (this.expiryDate == null) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.YEAR, 6);
            this.expiryDate = cal.getTime();
        }
        hasCvvEncrypted();
    }

    private void hasCvvEncrypted() {
        if (this.cvvEncrypted == null) {
            this.cvvEncrypted = String.valueOf(
                    (long) (Math.random() * Math.pow(10, 3)));
        }
    }
}
