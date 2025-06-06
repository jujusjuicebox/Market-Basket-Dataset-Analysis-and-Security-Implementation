package Project;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LDiversityChecker {

    public static boolean checkLDiversity(List<FileContent> transactions, int l) {
        Map<String, Set<String>> groupValues = new HashMap<>();

        for (FileContent transaction : transactions) {
            String key = generateKey(transaction);
            groupValues.putIfAbsent(key, new HashSet<>());
            groupValues.get(key).add(transaction.getProducts().toString());
        }

        for (Set<String> values : groupValues.values()) {
            if (values.size() < l) {
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
