package Project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SensitivityAndVarianceCalculator {

    public static void main(String[] args) {
        String filename = "MainTest1_Output.txt";
        List<Integer> totalItems = new ArrayList<>();
        List<Double> totalCosts = new ArrayList<>();

        try {
            // Reading the file
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            
            // Reading each line from the file
            while ((line = reader.readLine()) != null) {
                int indexStart = line.indexOf("]\",") + 3;
                if (indexStart != -1 && indexStart < line.length()) {
                    
                    int indexComma1 = line.indexOf(",", indexStart);
                    if (indexComma1 != -1) {
                       
                        String totalItemsStr = line.substring(indexStart, indexComma1).trim();
                        int totalItemsVal = Integer.parseInt(totalItemsStr);
                        totalItems.add(totalItemsVal);

                        int indexComma2 = line.indexOf(",", indexComma1 + 1);
                        if (indexComma2 != -1) {
                           
                            String totalCostStr = line.substring(indexComma1 + 1, indexComma2).trim();
                            double totalCostVal = Double.parseDouble(totalCostStr);
                            totalCosts.add(totalCostVal);
                        }
                    }
                }
            }
          
            reader.close();

            //sensitivity and variance
            double sensitivityTotalItems = calculateSensitivity(totalItems);
            double varianceTotalItems = calculateVariance(totalItems);
            double sensitivityTotalCosts = calculateSensitivity(totalCosts);
            double varianceTotalCosts = calculateVariance(totalCosts);

            
            System.out.println("Sensitivity of Total Items: " + sensitivityTotalItems);
            System.out.println("Variance of Total Items: " + varianceTotalItems);
            System.out.println("Sensitivity of Total Costs: " + sensitivityTotalCosts);
            System.out.println("Variance of Total Costs: " + varianceTotalCosts);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //calculating sensitivity
    private static double calculateSensitivity(List<? extends Number> data) {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (Number num : data) {
            double value = num.doubleValue();
            if (value > max) {
                max = value;
            }
            if (value < min) {
                min = value;
            }
        }

        return max - min;
    }

    //calculating variance
    private static double calculateVariance(List<? extends Number> data) {
        double sum = 0.0;
        double mean = 0.0;
        double variance = 0.0;
        int n = data.size();

        //mean
        for (Number num : data) {
            sum += num.doubleValue();
        }
        mean = sum / n;

        //variance
        for (Number num : data) {
            variance += Math.pow(num.doubleValue() - mean, 2);
        }
        variance /= n;

        return variance;
    }
}
