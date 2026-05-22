package cafeteria.person;

import cafeteria.order.Order;
import java.util.ArrayList;
import java.util.List;

public class Customer extends Person {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1000;
    
    // Attributes
    private int customerId;
    private int tableNumber;
    private double loyaltyPoints;
    private double totalSpent;
    private int visitCount;
    private String lastVisitDate;
    private List<Order> orderHistory;
    private boolean isVIP;
    
    // Constructors
    public Customer() {
        super();
        this.customerId = ++nextId;
        this.loyaltyPoints = 0;
        this.totalSpent = 0;
        this.visitCount = 0;
        this.orderHistory = new ArrayList<>();
        this.isVIP = false;
    }
    
    public Customer(String fullName, String phone, String email) {
        super(fullName, phone, email);
        this.customerId = ++nextId;
        this.loyaltyPoints = 0;
        this.totalSpent = 0;
        this.visitCount = 0;
        this.orderHistory = new ArrayList<>();
        this.isVIP = false;
    }
    
    public Customer(String fullName, String phone, String email, int tableNumber) {
        this(fullName, phone, email);
        this.tableNumber = tableNumber;
    }
    
    // Getters and Setters
    public int getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    
    public int getTableNumber() {
        return tableNumber;
    }
    
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
    
    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }
    
    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
        checkVIPStatus();
    }
    
    public double getTotalSpent() {
        return totalSpent;
    }
    
    public void setTotalSpent(double totalSpent) {
        this.totalSpent = totalSpent;
        checkVIPStatus();
    }
    
    public int getVisitCount() {
        return visitCount;
    }
    
    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }
    
    public String getLastVisitDate() {
        return lastVisitDate;
    }
    
    public void setLastVisitDate(String lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }
    
    public List<Order> getOrderHistory() {
        return orderHistory;
    }
    
    public boolean isVIP() {
        return isVIP;
    }
    
    // Business Methods
    public void selectItem() {
        System.out.println(fullName + " is selecting items from menu");
    }
    
    public void placeOrder(Order order) {
        orderHistory.add(order);
        visitCount++;
        totalSpent += order.getFinalAmount();
        loyaltyPoints += order.getFinalAmount() * 0.1; // 10% back as points
        checkVIPStatus();
        System.out.println("Order placed by " + fullName + ". Total: $" + order.getFinalAmount());
    }
    
    public void addLoyaltyPoints(double points) {
        this.loyaltyPoints += points;
        checkVIPStatus();
    }
    
    public boolean redeemLoyaltyPoints(double points) {
        if (points <= loyaltyPoints) {
            loyaltyPoints -= points;
            System.out.println("Redeemed " + points + " loyalty points");
            return true;
        }
        System.out.println("Insufficient loyalty points");
        return false;
    }
    
    private void checkVIPStatus() {
        boolean wasVIP = isVIP;
        isVIP = totalSpent >= 500 || loyaltyPoints >= 500;
        if (!wasVIP && isVIP) {
            System.out.println("Congratulations " + fullName + "! You are now a VIP customer!");
        }
    }
    
    public double getDiscountRate() {
        if (isVIP) return 0.15;
        if (loyaltyPoints >= 200) return 0.10;
        if (loyaltyPoints >= 100) return 0.05;
        return 0.0;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           CUSTOMER INFORMATION         ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Customer ID: %-30d║\n", customerId);
        System.out.printf("║ Name: %-35s║\n", fullName);
        System.out.printf("║ Phone: %-34s║\n", phone);
        System.out.printf("║ Email: %-34s║\n", email);
        System.out.printf("║ Table: %-34d║\n", tableNumber);
        System.out.printf("║ Loyalty Points: %-24.2f║\n", loyaltyPoints);
        System.out.printf("║ Total Spent: $%-26.2f║\n", totalSpent);
        System.out.printf("║ Visit Count: %-27d║\n", visitCount);
        System.out.printf("║ VIP Status: %-28s║\n", isVIP ? "YES" : "NO");
        System.out.printf("║ Discount Rate: %-25.0f%%║\n", getDiscountRate() * 100);
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    @Override
    public String getRole() {
        return "CUSTOMER";
    }
    
    @Override
    public String toString() {
        return String.format("[%d] %s - VIP: %s", customerId, fullName, isVIP ? "Yes" : "No");
    }
}