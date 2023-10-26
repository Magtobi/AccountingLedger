package com.pluralsight;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.ArrayList;
import java.time.DateTimeException;

import static com.pluralsight.HomeScreen.*;
public class Reader {
    static HashMap<Integer, Ledger> ledgerHashMap = new HashMap<>();
    static int transactionId = 0;

    public static void fileReader() throws IOException {

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
        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Read each line of the file until there are no more lines
        while ((input = bufferedReader.readLine()) != null) {
            // Split the line of data into
            String[] transactionReader = input.split("\\|");
            if (!transactionReader[0].contains("date")) {
                try {
                    dateCSV = LocalDate.parse(transactionReader[0], dateFormat);
                    timeCSV = LocalTime.parse(transactionReader[1], timeFormat);
                    descriptionCSV = transactionReader[2];
                    vendorCSV = transactionReader[3];
                    amountCSV = Double.parseDouble(transactionReader[4]);
                    //store details into hashmap
                    ledgerHashMap.put(transactionId, new Ledger(dateCSV, timeCSV, descriptionCSV, vendorCSV, amountCSV));
                    transactionId++;
                    ArrayList<Ledger> ledgerList = new ArrayList<>(ledgerHashMap.values());
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }
                bufferedReader.close();
            }
        }
    }
}


