package org.example.strategy;

import org.example.dao.service.CardService;

public class BlockCardStrategy implements MenuStrategy{
    private final CardService cardService = new CardService();
    @Override
    public void execute() {
        cardService.blockCard();
    }
}
