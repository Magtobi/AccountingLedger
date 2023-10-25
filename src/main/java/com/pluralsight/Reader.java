package com.pluralsight;
import java.io.*;
import java.time.*;
import java.util.HashMap;

import static com.pluralsight.HomeScreen.*;
public class Reader {
    static HashMap<Integer, Ledger> ledgerHashMap = new HashMap<>();
    static int transactionId = 0;
    public static void Reader() throws IOException {

        // Clear the HashMap
        ledgerHashMap.clear();


        // Create a variable to store each line of data from the file
        String input;
        LocalDateTime dateTimeCSV;
        String description;
        String vendor;
        double amount;

        // Create readers to read the transactions data from the file
        FileReader fr = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bf = new BufferedReader(fr);

        // Read each line of the file until there are no more lines
        while ((input = bf.readLine()) != null) {
            // Split the line of data into
            String[] transactionsReader = input.split("\\|");
            if (!transactionsReader[0].contains("date")) {
                dateTimeCSV = LocalDateTime.parse(transactionsReader[0], format);
                String descriptionCSV = transactionsReader[1];
                String vendorCSV = transactionsReader[2];
                double amountCSV = Double.parseDouble(transactionsReader[3]);
                ledgerHashMap.put(transactionId, new Ledger(dateTimeCSV, descriptionCSV, vendorCSV, amountCSV));
                transactionId++;
            }
        }
        bf.close();
    }
}
