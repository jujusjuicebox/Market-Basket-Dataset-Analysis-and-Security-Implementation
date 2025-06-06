package Project;

import java.util.List;

public class FileContent {

    private long transactionId;
    private List<String> products;
    private int totalItems;
    private double totalCost;
    private String city;
    private String storeType;
    private String customerCategory;
    private String season;

    public FileContent(long transactionId, List<String> products, int totalItems, double totalCost,
                       String city, String storeType, String customerCategory, String season) {
        this.transactionId = transactionId;
        this.products = products;
        this.totalItems = totalItems;
        this.totalCost = totalCost;
        this.city = city;
        this.storeType = storeType;
        this.customerCategory = customerCategory;
        this.season = season;
    }

    // Getters and setters
    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public List<String> getProducts() {
        return products;
    }

    public void setProducts(List<String> products) {
        this.products = products;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getCustomerCategory() {
        return customerCategory;
    }

    public void setCustomerCategory(String customerCategory) {
        this.customerCategory = customerCategory;
    }

    public String getSeason() {
        return season;
    }

    public void setSeason(String season) {
        this.season = season;
    }

    @Override
    public String toString() {
        return transactionId + ",\"" + products + "\"," + totalItems + "," + totalCost + "," + city + "," + storeType + "," + customerCategory + "," + season;
    }
}





