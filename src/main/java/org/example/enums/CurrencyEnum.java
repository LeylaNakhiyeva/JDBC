package org.example.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CurrencyEnum {
    AZN(1, "AZN"),
    USD(2, "USD"),
    RUB(3, "RUB");

    private final int value;
    private final String description;

    CurrencyEnum(int value, String description){
        this.value = value;
        this.description = description;
    }
    public static CurrencyEnum getCurrencyByValue(int value){
        return Arrays.stream(CurrencyEnum.values()).filter(enumObject ->enumObject.getValue()==value)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Invalid option : "+value));
    }

}
