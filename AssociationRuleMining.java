package Project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class AssociationRuleMining {

    public static void main(String[] args) {
        String inputFile = "PurchasedItems.txt";

        try {
            List<List<String>> transactions = readTransactions(inputFile);
            Map<String, Integer> itemFrequency = calculateItemFrequency(transactions);

            // Find top two most common items
            List<String> topTwoItems = findTopTwoItems(itemFrequency);

            // Count co-occurrences of the top two items
            int coOccurrenceCount = countCoOccurrences(transactions, topTwoItems.get(0), topTwoItems.get(1));

            // Calculate confidence for the association rule
            double confidence = calculateConfidence(transactions, itemFrequency, topTwoItems.get(0), topTwoItems.get(1));

            // Output results
            System.out.println("Top Two Most Common Items: " + topTwoItems);
            System.out.println("Co-occurrence count of " + topTwoItems.get(0) + " and " + topTwoItems.get(1) + ": " + coOccurrenceCount);
            System.out.println("Confidence of {" + topTwoItems.get(0) + "} -> {" + topTwoItems.get(1) + "}: " + confidence);

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    // Function to read transactions from file and extract items
    private static List<List<String>> readTransactions(String inputFile) throws IOException {
        List<List<String>> transactions = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            List<String> items = Arrays.asList(line.split(", "));
            transactions.add(items);
        }
        reader.close();

        return transactions;
    }

    // Function to calculate frequency of each item
    private static Map<String, Integer> calculateItemFrequency(List<List<String>> transactions) {
        Map<String, Integer> itemFrequency = new HashMap<>();

        for (List<String> transaction : transactions) {
            for (String item : transaction) {
                itemFrequency.put(item, itemFrequency.getOrDefault(item, 0) + 1);
            }
        }

        return itemFrequency;
    }

    // Function to find the top two most common items
    private static List<String> findTopTwoItems(Map<String, Integer> itemFrequency) {
        List<String> topTwoItems = new ArrayList<>();

        List<Map.Entry<String, Integer>> sortedItems = new ArrayList<>(itemFrequency.entrySet());
        sortedItems.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        if (!sortedItems.isEmpty()) {
            topTwoItems.add(sortedItems.get(0).getKey()); // Most frequent item
            if (sortedItems.size() > 1) {
                topTwoItems.add(sortedItems.get(1).getKey()); // Second most frequent item
            }
        }

        return topTwoItems;
    }

    // Function to count co-occurrences of two items in transactions
    private static int countCoOccurrences(List<List<String>> transactions, String item1, String item2) {
        int coOccurrenceCount = 0;

        for (List<String> transaction : transactions) {
            if (transaction.contains(item1) && transaction.contains(item2)) {
                coOccurrenceCount++;
            }
        }

        return coOccurrenceCount;
    }

    // Function to calculate confidence of the association rule 
    private static double calculateConfidence(List<List<String>> transactions, Map<String, Integer> itemFrequency, String item1, String item2) {
        
        double supportItem1Item2 = countCoOccurrences(transactions, item1, item2) / (double) transactions.size();

        double supportItem1 = itemFrequency.get(item1) / (double) transactions.size();

        return supportItem1Item2 / supportItem1;
    }
}
