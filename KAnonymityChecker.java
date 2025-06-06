package Project;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KAnonymityChecker {

    public static boolean checkKAnonymity(List<FileContent> transactions, int k) {
        Map<String, Integer> groupCounts = new HashMap<>();

        for (FileContent transaction : transactions) {
            String key = generateKey(transaction);
            groupCounts.put(key, groupCounts.getOrDefault(key, 0) + 1);
        }

        for (int count : groupCounts.values()) {
            if (count < k) {
                return false;
            }
        }

        return true;
    }

    private static String generateKey(FileContent transaction) {
        return transaction.getCity() + "|" + transaction.getStoreType() + "|" + 
               transaction.getCustomerCategory() + "|" + transaction.getSeason();
    }
}
