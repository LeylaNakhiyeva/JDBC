package org.example.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
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



}
