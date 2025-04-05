package org.example.util;

import org.example.enums.CurrencyEnum;

import java.util.Arrays;

public class CurrencyEnumUtil {
    public static void showCurrencyEnumItem(){
        Arrays.stream(CurrencyEnum.values())
                .forEach(currencyEnum -> {
                    System.out.println(currencyEnum.getValue()+"."+currencyEnum.getDescription());
                });
    }
}
