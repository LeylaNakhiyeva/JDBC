package org.example.dao.service;

import org.example.dao.model.Customer;
import org.example.dao.repository.CustomerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class UserService {

    private  final CustomerRepository customerRepository = new CustomerRepository();
    public void saveCustomer() {
        Scanner sc= new Scanner(System.in);
        System.out.print("Write the name: ");
        String name = sc.next();
        System.out.print("Write the surname: ");
        String surname = sc.next();
        System.out.print("Write the father name: ");
        String fatherName = sc.next();
        System.out.print("Write the birthdate: ");
        String birthDate = sc.next();


        Customer customer = Customer.builder()
                .name(name)
                .surname(surname)
                .patronymic(fatherName)
                .isActive(true)
                .createdAt(LocalDate.now())
                .birthDate(LocalDate.parse(birthDate))
                .build();

        customerRepository.saveCustomer(customer);
    }

    public List<Customer> fetchAllCustomers(){
       return customerRepository.fetchAllCustomers();
    }

    public void removeCustomer(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the customer name to remove: ");
        String customerName= sc.next();
        List<Customer> customerList = new ArrayList<>();
        customerList.removeIf(customer -> customer.getName().equals(customerName));
        System.out.println("Successful deletion");
    }

    public void updateCustomer(){
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the customer name to update: ");
        String customerName= sc.next();

        List<Customer> customerList = new ArrayList<>();
        customerList.stream().filter(customer -> customer.getName().equals(customerName))
                        .forEach(customer -> {
                            System.out.print("Enter the new values of name: ");
                            String newName = sc.next();
                            System.out.print("Enter the new values of surname: ");
                            String newSurname = sc.next();
                            System.out.println("Enter the new values of father name: ");
                            String patronymic = sc.next();
                            System.out.println("Enter the new values of birthdate: ");
                            String birthDate = sc.next();

                            customer = Customer.builder()
                                    .name(newName)
                                    .surname(newSurname)
                                    .patronymic(patronymic)
                                    .birthDate(LocalDate.parse(birthDate))
                                    .build();
                            customerRepository.saveCustomer(customer);
                        });

    }
}
