package org.example.strategy;

import org.example.dao.service.CardService;
import org.example.enums.CurrencyEnum;
import org.example.dao.model.Card;
import org.example.util.CurrencyEnumUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreateCardStrategy implements MenuStrategy {
    private final CardService cardService = new CardService();
    @Override
    public void execute() {
        cardService.saveCard();
    }

}