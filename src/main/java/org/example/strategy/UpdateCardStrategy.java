package org.example.strategy;

import org.example.enums.CurrencyEnum;
import org.example.dao.model.Card;
import org.example.util.CurrencyEnumUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UpdateCardStrategy implements MenuStrategy {
    @Override
    public void execute() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter card number: ");
        long cardNumber = sc.nextLong();
        List<Card> cards = new ArrayList<>();

        cards.stream().filter(card -> card.getCardNumber().equals(cardNumber))
                .forEach(card -> {
                    System.err.println("Enter new item : ");
                    long newCardNumber = 1000000000000000L + (long) (Math.random() * 9000000000000000L);
                    System.out.println("Card number: " + newCardNumber);
                    CurrencyEnumUtil.showCurrencyEnumItem();
                    System.out.println("Select :");
                    int option = sc.nextInt();
                    CurrencyEnum currency = CurrencyEnum.getCurrencyByValue(option);

                    card.setCardNumber(newCardNumber);
                    card.setCurrency(currency);
                    System.err.println(card);
                });


    }
}
