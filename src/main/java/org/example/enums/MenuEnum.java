package org.example.enums;

import lombok.Getter;
import org.example.strategy.*;

import java.util.Arrays;

@Getter
public enum MenuEnum {
    CREATE_CUSTOMER(1, "Create customer", new CreateCustomerStrategy()),
    REMOVE_CUSTOMER(2, "Remove customer", new RemoveCustomerStrategy()),
    UPDATE_CUSTOMER(3, "Update customer", new UpdateCustomerStrategy()),
    SHOW_ALL_CUSTOMER(4, "Show all customer", new ShowAllCustomerStrategy()),
    CREATE_CARD(5, "Create card", new CreateCardStrategy()),
    UPDATE_CARD(6, "Update card", new UpdateCardStrategy()),
    BLOCK_CARD(7, "Block card", new BlockCardStrategy()),
    UNBLOCK_CARD(8, "Unblock card", new ActiveCardStrategy()),
    CARD_TO_CARD(9, "Transfer money", new CardToCardStrategy()),
    SHOW_ALL_CARDS(10, "Show all cards", new ShowAllCardsStrategy()),
//    SHOW_TRANSACTION_BY_NO(11, "Find transaction by transaction number", ),
//    SHOW_ALL_CUSTOMER_TRANSACTION(12, "Show all customer transactions", ),
    STOP_PROGRAM(13, "Stop program", new StopProgramStrategy());

    private final int value;
    private final String description;
    private final MenuStrategy strategy;

    MenuEnum(int value, String description, MenuStrategy strategy){
        this.value=value;
        this.description=description;
        this.strategy = strategy;
    }


    public static MenuEnum getEnumByValue(int value){
      return   Arrays.stream(MenuEnum.values()).filter(enumObject -> enumObject.getValue()==value)
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("Invalid option : "+value));
    }
}
