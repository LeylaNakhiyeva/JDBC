package org.example.dao.service;

import org.example.dao.model.Card;
import org.example.dao.model.Customer;
import org.example.dao.repository.CardRepository;
import org.example.dao.repository.CustomerRepository;
import org.example.database.DatabaseConnection;
import org.example.enums.CardStatus;
import org.example.enums.CurrencyEnum;
import org.example.util.CurrencyEnumUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class CardService {
    private final CardRepository cardRepository = new CardRepository();
    private final CustomerRepository customerRepository = new CustomerRepository();

    public List<Card> fetchAllCards(){
        return cardRepository.fetchAllCards();

    }
    public void saveCard() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter customer id: ");
        Long customerId = scanner.nextLong();
        Customer customer = customerRepository.fetchAllCustomers().stream().filter(customer1 -> customer1.getId().equals(customerId)).findFirst().orElse(null);
            if (customer==null){
                System.out.println("Customer not found ");
            }else {
                System.out.println("Card number: ");
                long cardNumber = 1000000000000000L + (long) (Math.random() * 9000000000000000L);
                System.out.println("Enter cvv: ");
                String cvv = scanner.next();
                CurrencyEnumUtil.showCurrencyEnumItem();
                System.out.println("Select currency :");
                int option = scanner.nextInt();
                CurrencyEnum currency = CurrencyEnum.getCurrencyByValue(option);
                double balance = 100;
                long transactionNo = 0;
                boolean status = true;
                Card card = Card.builder()
                        .customerId(customer.getId())
                        .cardNumber(cardNumber)
                        .cvv(cvv)
                        .currency(currency)
                        .createdAt(LocalDate.now())
                        .isActive(status)
                        .balance(balance)     ////////
                        .transactionNo(transactionNo)
                        .build();

                cardRepository.saveCard(card);

            }
        }




    public void transfer(){
            Scanner sc= new Scanner(System.in);
            System.out.println("Enter your card number:");
            long cardNumber = sc.nextLong();

            Optional<Card> yourCard = fetchAllCards().stream()
                    .filter(card -> cardNumber==card.getCardNumber())
                    .findFirst();

            if (yourCard.isPresent()){
                System.out.println("Enter the recipient of the money: ");
                long cardNumberTo = sc.nextLong();
                Optional<Card> cardTo =  fetchAllCards().stream()
                        .filter(card -> cardNumberTo == card.getCardNumber())
                        .findFirst();

                if (cardTo.isPresent()){
                    System.out.println("Enter amount: ");
                    double amount = sc.nextDouble();
                    Card sender = yourCard.get();
                    Card recipient = cardTo.get();
                    if (sender.getIsActive() && recipient.getIsActive()){
                        sender.setBalance(sender.getBalance()-amount);
                        recipient.setBalance(recipient.getBalance()+amount);

                        long transactionNo = 1000000000000000L + (long) (Math.random() * 9000000000000000L);
                        sender.setTransactionNo(transactionNo);
                        recipient.setTransactionNo(transactionNo);

                        updateCardBalance(sender.getCardNumber(), sender.getBalance(), sender.getTransactionNo());
                        updateCardBalance(recipient.getCardNumber(), recipient.getBalance(), recipient.getTransactionNo());

        System.out.println("Transfer successful.");
                    }
                }
            }
        }

    public void updateCardBalance(Long cardNumber, Double newBalance, Long transactionNo) {
        String sql = "UPDATE card SET balance = ?, updated_at = ?, transaction_no = ? WHERE card_number = ?";
        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, newBalance);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.setLong(3, transactionNo);
            stmt.setLong(4, cardNumber);


            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update balance", e);
        }
    }




    public void updateCard(){
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

        public void blockCard(){
        Scanner sc = new Scanner(System.in);
            System.out.println("Enter card number to block");
            long cardNumber = sc.nextLong();
            Optional<Card> card = fetchAllCards().stream()
                    .filter(card1 -> card1.getCardNumber().equals(cardNumber))
                    .findFirst();

            Card card1 = card.get();
            card1.setIsActive(false);

            updateCardStatus(cardNumber, card1.getIsActive());
        }

    public void updateCardStatus(Long cardNumber, Boolean newStatus) {
        String sql = "UPDATE card SET is_active = ?, updated_at = ? WHERE card_number = ?";
        try (Connection conn = DatabaseConnection.getDatabaseConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, newStatus);
            stmt.setDate(2, Date.valueOf(LocalDate.now()));
            stmt.setLong(3, cardNumber);

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update status", e);
        }
    }

    public void activeCard(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter card number to active: ");
        long cardNumber = sc.nextLong();
        Optional<Card> card = fetchAllCards().stream().filter(card1 -> card1.getCardNumber().equals(cardNumber)).findFirst();
        Card card1 = card.get();
        card1.setIsActive(true);

        updateCardStatus(cardNumber, card1.getIsActive());
    }

}
