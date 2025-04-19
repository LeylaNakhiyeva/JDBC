package org.example.strategy;

import org.example.dao.service.CardService;

public class ShowAllCardsStrategy implements MenuStrategy{
    private  final CardService cardService = new CardService();
    @Override
    public void execute() {
        cardService.fetchAllCards().forEach(System.out::println);
    }
}