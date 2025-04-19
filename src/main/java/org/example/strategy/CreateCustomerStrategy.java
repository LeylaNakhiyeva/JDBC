package org.example.strategy;

import org.example.dao.service.UserService;


public class CreateCustomerStrategy implements MenuStrategy{
    private final UserService userService = new UserService();
    @Override
    public void execute(){
        userService.saveCustomer();
    }
}
