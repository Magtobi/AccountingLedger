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


        // Create readers to read the transactions data from the file
        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        // Read each line of the file until there are no more lines
        while ((input = bufferedReader.readLine()) != null) {
            // Split the line of data
            String[] transactionReader = input.split("\\|");
            if (!transactionReader[0].contains("date")) {
                try {
                    // Parse the CSV data into variables
                    dateCSV = LocalDate.parse(transactionReader[0]); // Extract and convert the date
                    timeCSV = LocalTime.parse(transactionReader[1]); // Extract and convert the time
                    descriptionCSV = transactionReader[2]; // Extract the description
                    vendorCSV = transactionReader[3]; // Extract the vendor
                    amountCSV = Double.parseDouble(transactionReader[4]); // Extract and convert the amount to a double

                    //store extracted details into a HashMap with a transactionId
                    ledgerHashMap.put(transactionId, new Ledger(dateCSV, timeCSV, descriptionCSV, vendorCSV, amountCSV));
                    transactionId++;

                    // Update the ledgerList which is an ArrayList of ledger entries
                    ledgerList = new ArrayList<>(ledgerHashMap.values());

                    // If there's an issue with number formatting, raise a runtime exception
                } catch (NumberFormatException e) {
                    throw new RuntimeException(e);
                }

            }
        }
        // Close BufferedReader
        bufferedReader.close();
    }
}


