package Project;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MainTest1 {

    public static void main(String[] args) {
       
        List<FileContent> originalTransactions = ReadFile.readData("Retail_Transaction_Dataset.txt");

        //apply anonymization
        List<FileContent> anonymizedTransactions = Anonymization.anonymize(originalTransactions);

        //apply differential privacy
        List<FileContent> privateTransactions = DifferentialPrivacy.addNoise(anonymizedTransactions);

        //Write the anonymized and differentially private data to output file
        writeOutputToFile(privateTransactions, "MainTest1_Output.txt");


        System.out.println("Anonymization and Differential Privacy applied successfully. Check MainTest1_Output.txt");
    }

    private static void writeOutputToFile(List<FileContent> transactions, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (FileContent transaction : transactions) {
                writer.write(transaction.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
