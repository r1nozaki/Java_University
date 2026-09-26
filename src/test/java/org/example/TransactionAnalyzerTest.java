package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TransactionAnalyzerTest {

    @Test
    public void testCalculateTotalBalance() {
        Transaction t1 = new Transaction("01-01-2023", 100.0, "Дохід");
        Transaction t2 = new Transaction("02-01-2023", -50.0, "Витрата");
        Transaction t3 = new Transaction("03-01-2023", 150.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        double result = TransactionAnalyzer.calculateTotalBalance(transactions);
        Assertions.assertEquals(200.0, result, "Розрахунок загального балансу неправильний");
    }

    @Test
    public void testCountTransactionsByMonth() {
        Transaction t1 = new Transaction("01-02-2023", 50.0, "Дохід");
        Transaction t2 = new Transaction("15-02-2023", -20.0, "Витрата");
        Transaction t3 = new Transaction("05-03-2023", 100.0, "Дохід");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        int countFeb = TransactionAnalyzer.countTransactionsByMonth(transactions, "02-2023");
        int countMar = TransactionAnalyzer.countTransactionsByMonth(transactions, "03-2023");

        Assertions.assertEquals(2, countFeb);
        Assertions.assertEquals(1, countMar);
    }

    @Test
    public void testFindTopExpenses() {
        Transaction t1 = new Transaction("01-01-2023", -100.0, "Магазин");
        Transaction t2 = new Transaction("02-01-2023", -500.0, "Оренда");
        Transaction t3 = new Transaction("03-01-2023", -200.0, "Кафе");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);

        Assertions.assertEquals(3, topExpenses.size());
        Assertions.assertEquals(-500.0, topExpenses.get(0).getAmount()); // Найбільша витрата за модулем перша
    }

    @Test
    public void testFindMaxAndMinExpenseForPeriod() {
        Transaction t1 = new Transaction("05-12-2023", -450.0, "Сільпо");
        Transaction t2 = new Transaction("07-12-2023", -120.0, "Аптека");
        Transaction t3 = new Transaction("14-12-2023", -3200.0, "Оренда");
        List<Transaction> transactions = Arrays.asList(t1, t2, t3);

        LocalDate start = LocalDate.of(2023, 12, 1);
        LocalDate end = LocalDate.of(2023, 12, 31);

        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseForPeriod(transactions, start, end);
        Transaction minExpense = TransactionAnalyzer.findMinExpenseForPeriod(transactions, start, end);

        Assertions.assertNotNull(maxExpense);
        Assertions.assertEquals(-3200.0, maxExpense.getAmount());

        Assertions.assertNotNull(minExpense);
        Assertions.assertEquals(-120.0, minExpense.getAmount());
    }
}