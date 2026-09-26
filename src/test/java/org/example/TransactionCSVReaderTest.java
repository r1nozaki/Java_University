package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TransactionCSVReaderTest {

    @Test
    public void testReadTransactions() {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";
        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        Assertions.assertNotNull(transactions, "Список транзакцій не повинен бути null");
        Assertions.assertFalse(transactions.isEmpty(), "Список транзакцій не повинен бути порожнім");
    }
}