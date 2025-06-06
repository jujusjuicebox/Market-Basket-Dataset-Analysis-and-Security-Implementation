package Project;

import java.util.List;

public class Anonymization {

    public static List<FileContent> anonymize(List<FileContent> transactions) {
        for (FileContent transaction : transactions) {
            generalizeFields(transaction);
        }
        return transactions;
    }

    private static void generalizeFields(FileContent transaction) {
        generalizeCity(transaction);
        generalizeCustomerCategory(transaction);
    }

    private static void generalizeCity(FileContent transaction) {
        String city = transaction.getCity().toLowerCase();
        String region;

        if (city.contains("new york") || city.contains("boston") || city.contains("chicago")) {
            region = "Northeast";
        } else if (city.contains("los angeles") || city.contains("san francisco") || city.contains("san diego")) {
            region = "West";
        } else if (city.contains("houston") || city.contains("dallas") || city.contains("austin")) {
            region = "South";
        } else {
            region = "Other";
        }

        transaction.setCity(region);
    }

    private static void generalizeCustomerCategory(FileContent transaction) {
        String category = transaction.getCustomerCategory().toLowerCase();
        String ageGroup;

        if (category.contains("student") || category.contains("teenager") || category.contains("young adult")) {
            ageGroup = "<22";
        } else if (category.contains("homemaker")) {
            ageGroup = "22-55";
        } else if (category.contains("senior citizen") || category.contains("retiree")) {
            ageGroup = "55+";
        } else {
            ageGroup = "Unknown";
        }

        transaction.setCustomerCategory(ageGroup);
    }
}


