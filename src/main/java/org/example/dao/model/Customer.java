package org.example.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class Customer {
    private static long serialVersionUUID = 1L;
    private Long id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private Boolean isActive;
//    private List<Card> cards = new ArrayList<>();

}