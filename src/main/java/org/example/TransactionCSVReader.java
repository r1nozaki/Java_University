package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public abstract class TransactionCSVReader {

    private TransactionCSVReader() {
    }

    public static List<Transaction> readTransactions(String filePath) {
        List<Transaction> transactions = new ArrayList<>();
        try {
            URL url = new URL(filePath);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
                String line;
                br.readLine();
                while ((line = br.readLine()) != null) {
                    if (line.trim().isEmpty()) continue;
                    String[] values = line.split(",");
                    Transaction transaction = new Transaction(
                            values[0].trim(),
                            Double.parseDouble(values[1].trim()),
                            values[2].trim()
                    );
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return transactions;
    }
}