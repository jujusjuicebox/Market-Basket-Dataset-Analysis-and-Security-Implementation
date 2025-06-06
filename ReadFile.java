package Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<FileContent> readData(String fileName) {
        List<FileContent> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                FileContent transaction = parseLine(line);
                if (transaction != null) {
                    transactions.add(transaction);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        return transactions;
    }

    private static FileContent parseLine(String line) {
        String[] parts = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)"); //ignoring commas inside quotes

        if (parts.length < 11) {
            // Handle cases where line doesn't have enough data
            return null;
        }

        try {
            long transactionId = Long.parseLong(parts[0].trim());
            String date = parts[1].trim();
            String customerName = parts[2].trim();
            List<String> products = parseProducts(parts[3].trim());
            int totalItems = Integer.parseInt(parts[4].trim());
            double totalCost = Double.parseDouble(parts[5].trim());
            String paymentMethod = parts[6].trim();
            String city = parts[7].trim();
            String storeType = parts[8].trim();
            String customerCategory = parts[9].trim();
            String season = parts[10].trim();

            // Creating and returning FileContent object
            return new FileContent(transactionId, products, totalItems, totalCost, city, storeType, customerCategory, season);

        } catch (NumberFormatException e) {
            System.err.println("Error parsing line: " + line + ", " + e.getMessage());
            return null;
        }
    }

    private static List<String> parseProducts(String productsString) {
        List<String> products = new ArrayList<>();
        productsString = productsString.replace("[", "").replace("]", "").replace("\"", ""); //removing brackets and quotes
        String[] productArray = productsString.split(", ");
        for (String product : productArray) {
            products.add(product.trim());
        }
        return products;
    }
}






