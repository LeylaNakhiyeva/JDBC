package org.example.strategy;

import org.example.dao.service.CardService;

import java.util.Scanner;

public class ShowAllCardsStrategy implements MenuStrategy{
    private  final CardService cardService = new CardService();
    @Override
    public void execute() {
        cardService.fetchAllCards().forEach(System.out::println);
    }
}