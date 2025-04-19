package org.example.dao.service;

import org.example.dao.model.Customer;
import org.example.dao.repository.CustomerRepository;
import org.example.database.DatabaseConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        System.out.println("Successful operation");
    }

    public List<Customer> fetchAllCustomers(){
        return customerRepository.fetchAllCustomers();
    }

    public void removeCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the customer name to remove: ");
        String customerName = sc.next();

        String sql = "DELETE FROM customer WHERE name = ?";

        try (
                Connection connection = DatabaseConnection.getDatabaseConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, customerName);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Successful deletion");
            } else {
                System.out.println("Customer not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void updateCustomer() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the customer name to update: ");
        String customerName = sc.next();

        System.out.print("Enter the new name: ");
        String newName = sc.next();

        System.out.print("Enter the new surname: ");
        String newSurname = sc.next();

        System.out.print("Enter the new patronymic: ");
        String newPatronymic = sc.next();

        System.out.print("Enter the new birth date (yyyy-MM-dd): ");
        String birthDateStr = sc.next();

        String sql = "UPDATE customer SET name = ?, surname = ?, patronymic = ?, birth_date = ?, updated_at = ? WHERE name = ?";

        try (
                Connection connection = DatabaseConnection.getDatabaseConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, newName);
            statement.setString(2, newSurname);
            statement.setString(3, newPatronymic);
            statement.setDate(4, Date.valueOf(LocalDate.parse(birthDateStr)));
            statement.setDate(5, Date.valueOf(LocalDate.now())); // updated_at tarixi indiki gün
            statement.setString(6, customerName);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Customer updated successfully");
            } else {
                System.out.println("Customer not found");
            }

        } catch (SQLException e) {
            System.out.println("Error while updating customer: " + e.getMessage());
        }
    }

}