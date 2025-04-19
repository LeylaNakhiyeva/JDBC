package org.example.dao.model;

import lombok.*;
import org.example.enums.CurrencyEnum;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder

public class Card {
    private Long id;
    private Long customerId;
    private Long cardNumber;
    private String cvv;
    private CurrencyEnum currency;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;
    private Double balance;
    private Long transactionNo;

}
