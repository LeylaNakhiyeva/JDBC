package org.example.strategy;

import java.util.Scanner;

public class CardToCardStrategy implements MenuStrategy{
    @Override
    public void execute() {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter card number to :");
        long cardNumber = sc.nextLong();


    }
}
