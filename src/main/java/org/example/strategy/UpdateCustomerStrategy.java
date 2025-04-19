package org.example.strategy;

import org.example.dao.service.UserService;

public class UpdateCustomerStrategy implements MenuStrategy {
    private final UserService userService = new UserService();

    @Override
    public void execute() {
        userService.updateCustomer();

    }
}
