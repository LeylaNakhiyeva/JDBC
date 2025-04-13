package org.example.dao.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Card> cards;

    public Customer(Long id, String name, String surname, String patronymic, LocalDate birthDate, LocalDate createdAt, LocalDate updatedAt, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
    }
}