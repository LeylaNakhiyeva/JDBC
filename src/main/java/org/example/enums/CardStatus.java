package org.example.enums;

import lombok.Getter;

import java.util.Arrays;
@Getter
public enum CardStatus {
    BLOCK(1, "Block card"),
    ACTIVE(2, "Active card");

    private final int value;
    private final String description;

    CardStatus(int value, String description){
        this.value = value;
        this.description = description;
    }
    public static CardStatus getStatusByValue(int value){
        return Arrays.stream(CardStatus.values()).filter(enumObject ->enumObject.getValue()==value)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Invalid option : "+value));
    }
}
