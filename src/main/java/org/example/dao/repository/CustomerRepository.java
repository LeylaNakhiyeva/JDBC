package org.example.dao.repository;

import org.example.dao.model.Customer;
import org.example.database.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {
    public void saveCustomer(Customer customer) {
        try (Connection connection = DatabaseConnection.getDatabaseConnection()) {
            PreparedStatement prepareStatement = connection.prepareStatement("insert into customer(name, surname, patronymic, birth_date, created_at, is_active) values(?, ?, ?, ?, ?, ?)");
            buildPreparedStatement(prepareStatement, customer);

            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void buildPreparedStatement(PreparedStatement prepareStatement, Customer customer) throws SQLException {
        prepareStatement.setString(1, customer.getName());
        prepareStatement.setString(2, customer.getSurname());
        prepareStatement.setString(3, customer.getPatronymic());
        prepareStatement.setDate(4, Date.valueOf(customer.getBirthDate()));
        prepareStatement.setDate(5, Date.valueOf(customer.getCreatedAt()));
        prepareStatement.setBoolean(6, customer.getIsActive());
    }

    public List<Customer> fetchAllCustomers(){

        try (Connection connection = DatabaseConnection.getDatabaseConnection();
             PreparedStatement prepareStatement = connection.prepareStatement("SELECT* FROM customer");
             ResultSet rs = prepareStatement.executeQuery()) {
            List<Customer> customerList = new ArrayList<>();
            while (rs.next()){
                Customer customer = buildCustomerFromResultSet(rs);
                customerList.add(customer);
            }
            return customerList;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Customer buildCustomerFromResultSet(ResultSet rs) throws SQLException {

        Long id = rs.getLong("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String patronymic = rs.getString("patronymic");
        LocalDate birthDate = rs.getDate("birth_date").toLocalDate();
        LocalDate createdAt = rs.getDate("created_at").toLocalDate();
        LocalDate updateAt = rs.getDate("updated_at")== null ? null : rs.getDate("updated_at").toLocalDate();
        Boolean isActive = rs.getBoolean("is_active");

        return  new Customer(id, name, surname, patronymic, birthDate, createdAt, updateAt, isActive);
    }
}
