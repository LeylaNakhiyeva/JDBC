package org.example.strategy;

import org.example.dao.service.UserService;

import java.util.Scanner;

public class UpdateCustomerStrategy implements MenuStrategy {
    private final UserService userService = new UserService();

    @Override
    public void execute() {
        userService.updateCustomer();

    }
}
