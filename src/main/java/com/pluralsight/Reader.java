package com.pluralsight;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
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
        LocalDate dateCSV;
        LocalTime timeCSV;
        String descriptionCSV;
        String vendorCSV;
        double amountCSV;

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Create readers to read the transactions data from the file
        FileReader fr = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bf = new BufferedReader(fr);

        // Read each line of the file until there are no more lines
        while ((input = bf.readLine()) != null) {
            // Split the line of data into
            String[] transactionsReader = input.split("\\|");
            if (transactionsReader.length >= 5) {
                if (!transactionsReader[0].contains("date")) {
                    try {
                        dateCSV = LocalDate.parse(transactionsReader[0], dateFormat);
                        timeCSV = LocalTime.parse(transactionsReader[1], timeFormat);
                        descriptionCSV = transactionsReader[2];
                        vendorCSV = transactionsReader[3];
                        amountCSV = Double.parseDouble(transactionsReader[4]);
                        ledgerHashMap.put(transactionId, new Ledger(dateCSV, timeCSV, descriptionCSV, vendorCSV, amountCSV));
                        transactionId++;
                    } catch (Exception e) {

                    }
                }
            }
        }
        bf.close();
    }
}

