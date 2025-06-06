package Project;

import java.io.*;
import java.util.*;

public class ExtractPurchasedItems {

    public static void main(String[] args) {
        String inputFile = "MainTest1_Output.txt";
        String outputFile = "PurchasedItems.txt";

        try {
            List<String> itemsPurchased = extractItemsPurchased(inputFile);
            writeItemsToFile(itemsPurchased, outputFile);

            System.out.println("Items purchased extracted and saved to: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    // Function to extract items purchased from MainTest1_Output.txt
    private static List<String> extractItemsPurchased(String inputFile) throws IOException {
        List<String> itemsPurchased = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String line;
        while ((line = reader.readLine()) != null) {
            String items = extractItems(line);
            itemsPurchased.add(items);
        }
        reader.close();

        return itemsPurchased;
    }

    // Function to extract items from a transaction line
    private static String extractItems(String line) {
        String items = "";
        int start = line.indexOf("['");
        int end = line.indexOf("']", start);
        if (start != -1 && end != -1) {
            items = line.substring(start + 2, end);
            items = items.replaceAll("', '", ", "); 
        }
        return items;
    }

    // Function to write items purchased to a file
    private static void writeItemsToFile(List<String> itemsPurchased, String outputFile) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
        for (String item : itemsPurchased) {
            writer.write(item);
            writer.newLine();
        }
        writer.close();
    }
}
