package org.example.strategy;

import org.example.dao.model.Customer;
import org.example.dao.service.UserService;

import java.util.Scanner;

public class CreateCustomerStrategy implements MenuStrategy{
    private final UserService userService = new UserService();
    @Override
    public void execute(){
        userService.saveCustomer();
    }
}
