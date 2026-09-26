package org.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePath = "https://informer.com.ua/dut/java/pr2.csv";

        List<Transaction> transactions = TransactionCSVReader.readTransactions(filePath);

        double totalBalance = TransactionAnalyzer.calculateTotalBalance(transactions);
        TransactionReportGenerator.printBalanceReport(totalBalance);

        String monthYear = "01-2024";
        int transactionsCount = TransactionAnalyzer.countTransactionsByMonth(transactions, monthYear);
        TransactionReportGenerator.printTransactionsCountByMonth(monthYear, transactionsCount);

        List<Transaction> topExpenses = TransactionAnalyzer.findTopExpenses(transactions);
        TransactionReportGenerator.printTopExpensesReport(topExpenses);

        LocalDate startDate = LocalDate.of(2023, 12, 1);
        LocalDate endDate = LocalDate.of(2023, 12, 31);

        Transaction maxExpense = TransactionAnalyzer.findMaxExpenseForPeriod(transactions, startDate, endDate);
        TransactionReportGenerator.printPeriodExpenseReport("Найбільша витрата за грудень 2023", maxExpense);

        Transaction minExpense = TransactionAnalyzer.findMinExpenseForPeriod(transactions, startDate, endDate);
        TransactionReportGenerator.printPeriodExpenseReport("Найменша витрата за грудень 2023", minExpense);

        Map<String, Double> expensesByCategory = TransactionAnalyzer.calculateExpensesByCategory(transactions);
        TransactionReportGenerator.printVisualExpenseReport("Витрати по категоріях", expensesByCategory);

        Map<String, Double> expensesByMonth = TransactionAnalyzer.calculateExpensesByMonth(transactions);
        TransactionReportGenerator.printVisualExpenseReport("Витрати по місяцях", expensesByMonth);
    }
}