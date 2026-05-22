package cafeteria.person;

import cafeteria.order.Order;
import cafeteria.order.Payment;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Cashier extends Person {
    private static final long serialVersionUID = 1L;
    private static int nextId = 500;
    
    // Attributes
    private int cashierId;
    private String shift; // MORNING, EVENING, NIGHT
    private String department;
    private String employeeCode;
    private String hireDate;
    private boolean isActive;
    private int ordersProcessed;
    private double totalRevenueProcessed;
    
    // Constructors
    public Cashier() {
        super();
        this.cashierId = ++nextId;
        this.shift = "MORNING";
        this.department = "Front Counter";
        this.employeeCode = generateEmployeeCode();
        this.hireDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.isActive = true;
        this.ordersProcessed = 0;
        this.totalRevenueProcessed = 0;
    }
    
    public Cashier(String fullName, String phone, String email, String shift) {
        super(fullName, phone, email);
        this.cashierId = ++nextId;
        this.shift = shift;
        this.department = "Front Counter";
        this.employeeCode = generateEmployeeCode();
        this.hireDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.isActive = true;
        this.ordersProcessed = 0;
        this.totalRevenueProcessed = 0;
    }
    
    public Cashier(String fullName, String phone, String email, String shift, String department) {
        this(fullName, phone, email, shift);
        this.department = department;
    }
    
    // Generate unique employee code
    private String generateEmployeeCode() {
        return "CASH" + String.format("%04d", cashierId);
    }
    
    // Getters and Setters
    public int getCashierId() {
        return cashierId;
    }
    
    public String getShift() {
        return shift;
    }
    
    public void setShift(String shift) {
        this.shift = shift;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getEmployeeCode() {
        return employeeCode;
    }
    
    public String getHireDate() {
        return hireDate;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    public int getOrdersProcessed() {
        return ordersProcessed;
    }
    
    public double getTotalRevenueProcessed() {
        return totalRevenueProcessed;
    }
    
    // Business Methods
    public void generateBill(Order order) {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║              BILL INVOICE              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Cashier: %-32s║\n", fullName);
        System.out.printf("║ Employee Code: %-26s║\n", employeeCode);
        System.out.printf("║ Order ID: %-31d║\n", order.getOrderId());
        System.out.printf("║ Date: %-35s║\n", order.getOrderDate());
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Subtotal: $%-30.2f║\n", order.getTotalAmount());
        System.out.printf("║ Tax (10%%): $%-28.2f║\n", order.getTaxAmount());
        System.out.printf("║ Service (5%%): $%-26.2f║\n", order.getServiceCharge());
        System.out.printf("║ Discount: $%-30.2f║\n", order.getDiscountAmount());
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ TOTAL: $%-33.2f║\n", order.getFinalAmount());
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    public void printReceipt(Payment payment, Order order) {
        payment.printReceipt();
        System.out.println("\nOrder Details:");
        order.displayOrder();
    }
    
    public boolean processPayment(Payment payment, Order order) {
        boolean success = payment.makePayment();
        if (success) {
            ordersProcessed++;
            totalRevenueProcessed += payment.getAmount();
            System.out.println("Payment processed successfully by " + fullName);
        }
        return success;
    }
    
    public boolean validateOrder(Order order) {
        if (order == null) return false;
        if (order.getOrderItems().isEmpty()) return false;
        if (order.getFinalAmount() <= 0) return false;
        return true;
    }
    
    public String getPerformanceReport() {
        return String.format(
            "Cashier Performance:\n" +
            "Name: %s\n" +
            "Employee Code: %s\n" +
            "Orders Processed: %d\n" +
            "Total Revenue: $%.2f\n" +
            "Average Order Value: $%.2f",
            fullName, employeeCode, ordersProcessed, totalRevenueProcessed,
            ordersProcessed > 0 ? totalRevenueProcessed / ordersProcessed : 0
        );
    }
    
    @Override
    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           CASHIER INFORMATION          ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Cashier ID: %-29d║\n", cashierId);
        System.out.printf("║ Name: %-35s║\n", fullName);
        System.out.printf("║ Employee Code: %-26s║\n", employeeCode);
        System.out.printf("║ Shift: %-34s║\n", shift);
        System.out.printf("║ Department: %-29s║\n", department);
        System.out.printf("║ Hire Date: %-30s║\n", hireDate);
        System.out.printf("║ Status: %-33s║\n", isActive? "ACTIVE" : "INACTIVE");
        System.out.printf("║ Orders: %-33d║\n", ordersProcessed);
        System.out.printf("║ Revenue: $%-31.2f║\n", totalRevenueProcessed);
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    @Override
    public String getRole() {
        return "CASHIER";
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - %s Shift", employeeCode, fullName, shift);
    }
}