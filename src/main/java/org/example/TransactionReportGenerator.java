package org.example;

import java.util.List;
import java.util.Map;

public abstract class TransactionReportGenerator {

    private TransactionReportGenerator() {}

    public static void printBalanceReport(double totalBalance) {
        System.out.println("Загальний баланс: " + totalBalance);
    }

    public static void printTransactionsCountByMonth(String monthYear, int count) {
        System.out.println("Кількість транзакцій за " + monthYear + ": " + count);
    }

    public static void printTopExpensesReport(List<Transaction> topExpenses) {
        System.out.println("\n--- 10 найбільших витрат ---");
        for (Transaction expense : topExpenses) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount() + " (" + expense.getDate() + ")");
        }
    }

    public static void printPeriodExpenseReport(String title, Transaction expense) {
        System.out.println("\n--- " + title + " ---");
        if (expense != null) {
            System.out.println(expense.getDescription() + ": " + expense.getAmount() + " (Дата: " + expense.getDate() + ")");
        } else {
            System.out.println("Витрат за вказаний період не знайдено.");
        }
    }

    public static void printVisualExpenseReport(String title, Map<String, Double> expenseData) {
        System.out.println("\n=================================");
        System.out.println("ЗВІТ: " + title);
        System.out.println("Примітка: 1 '*' = 1000 грн");
        System.out.println("=================================");

        for (Map.Entry<String, Double> entry : expenseData.entrySet()) {
            double amount = entry.getValue();
            int starsCount = (int) (amount / 1000);
            String stars = "*".repeat(Math.max(0, starsCount));
            System.out.printf("%-20s | %-10.2f грн | %s%n", entry.getKey(), amount, stars);
        }
    }
}