package org.example.dao.service;

import org.example.dao.model.Card;
import org.example.dao.model.Customer;
import org.example.dao.repository.CardRepository;
import org.example.dao.repository.CustomerRepository;
import org.example.enums.CurrencyEnum;
import org.example.util.CurrencyEnumUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CardService {
    private final CardRepository cardRepository = new CardRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    public void saveCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer name: ");
        String customerName = scanner.next();
        List<Customer> customerList = customerRepository.fetchAllCustomers();
        customerList.stream().filter(customer -> customer.getName().equals(customerName)).findFirst().orElse(null);
            System.out.println("enter customer id: ");
            Long customerId = scanner.nextLong();
            System.out.println("Card number: ");
            long cardNumber = 1000000000000000L + (long) (Math.random() * 9000000000000000L);
            System.out.println("Enter cvv: ");
            String cvv = scanner.next();
            CurrencyEnumUtil.showCurrencyEnumItem();
            System.out.println("Select currency :");
            int option = scanner.nextInt();
            CurrencyEnum currency = CurrencyEnum.getCurrencyByValue(option);

            Card card = Card.builder()
                    .customerId(customerId)
                    .cardNumber(cardNumber)
                    .cvv(cvv)
                    .currency(currency)
                    .createdAt(LocalDate.now())
                    .isActive(true)
                    .build();

            cardRepository.saveCard(card);


        }

    public List<Card> fetchAllCards(){
        return cardRepository.fetchAllCards();

    }
}
