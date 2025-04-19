package org.example.dao.repository;

import org.example.dao.model.Card;
import org.example.database.DatabaseConnection;
import org.example.enums.CurrencyEnum;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {
    public void saveCard(Card card){
        try (Connection connection = DatabaseConnection.getDatabaseConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("insert into card(customer_id, card_number, cvv, currency, created_at, is_active, balance, transaction_no) values(?, ?, ?, ?, ?, ?,?,?)");
            buildPreparedStatement(preparedStatement, card);
            preparedStatement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }

    private void buildPreparedStatement(PreparedStatement preparedStatement, Card card) throws SQLException {
        preparedStatement.setLong(1, card.getCustomerId());
        preparedStatement.setLong(2, card.getCardNumber());
        preparedStatement.setString(3, card.getCvv());
        preparedStatement.setObject(4, card.getCurrency().name(), java.sql.Types.VARCHAR);
        preparedStatement.setDate(5, Date.valueOf(card.getCreatedAt()));
        preparedStatement.setBoolean(6, card.getIsActive());
        preparedStatement.setDouble(7,card.getBalance());
        preparedStatement.setLong(8,card.getTransactionNo());

    }

    public List<Card> fetchAllCards(){
        try(Connection connection = DatabaseConnection.getDatabaseConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT* FROM card");
            ResultSet resultSet = preparedStatement.executeQuery()){
            List<Card> cardList = new ArrayList<>();
            while (resultSet.next()){
                Card card = buildCardFromResultSet(resultSet);
                cardList.add(card);
            }
            return cardList;

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private Card buildCardFromResultSet(ResultSet rs) throws SQLException {

        Long id = rs.getLong("id");
        Long customerId = rs.getLong("customer_id");
        Long card_number = rs.getLong("card_number");
        String cvv = rs.getString("cvv");
        CurrencyEnum currency = CurrencyEnum.valueOf(rs.getString("currency"));
        LocalDate createdAt = rs.getDate("created_at").toLocalDate();
        LocalDate updatedAt = rs.getDate("updated_at")== null ? null : rs.getDate("updated_at").toLocalDate();
        Boolean isActive = rs.getBoolean("is_active");
        Double balance = rs.getDouble("balance");
        Long transactionNo = rs.getLong("transaction_no");

        return  new Card(id, customerId, card_number, cvv, currency, createdAt, updatedAt, isActive,balance, transactionNo);
    }
}
