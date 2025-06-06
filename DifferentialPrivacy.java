package Project;

import java.util.List;
import java.util.Random;

public class DifferentialPrivacy {

    private static final double EPSILON = 1.0; // Privacy budget parameter
    private static final Random random = new Random();

    public static List<FileContent> addNoise(List<FileContent> transactions) {
        for (FileContent transaction : transactions) {
            addNoiseToTotalCost(transaction);
            addNoiseToTotalItems(transaction);
        }

        return transactions;
    }

    private static void addNoiseToTotalCost(FileContent transaction) {
        double originalCost = transaction.getTotalCost();
        double noise = laplaceMechanism(0, EPSILON);
        double noisyCost = originalCost + noise;

        transaction.setTotalCost(noisyCost);
    }

    private static void addNoiseToTotalItems(FileContent transaction) {
        int originalItems = transaction.getTotalItems();
        double noise = laplaceMechanism(0, EPSILON);
        int noisyItems = (int) (originalItems + noise);

        transaction.setTotalItems(noisyItems);
    }

    // Laplace mechanism for differential privacy
    private static double laplaceMechanism(double mu, double epsilon) {
        double b = 1 / epsilon;
        double u = random.nextDouble() - 0.5; // Uniform distribution over [-0.5, 0.5]
        return mu - b * Math.signum(u) * Math.log(1 - 2 * Math.abs(u));
    }
}




