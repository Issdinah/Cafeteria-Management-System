package cafeteria.order;

import cafeteria.person.Customer;
import cafeteria.person.Cashier;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private static int nextId = 1000;
    
    // Attributes
    private int orderId;
    private String orderDate;
    private double totalAmount;
    private double taxAmount;
    private double discountAmount;
    private double serviceCharge;
    private double finalAmount;
    private String status; // PENDING, PREPARING, READY, COMPLETED, CANCELLED
    private Customer customer;
    private Cashier cashier;
    private List<OrderItem> orderItems;
    private Payment payment;
    private String specialInstructions;
    
    // Constants
    private static final double TAX_RATE = 0.10;
    private static final double SERVICE_RATE = 0.05;
    
    // Constructors
    public Order() {
        this.orderId = ++nextId;
        this.orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.status = "PENDING";
        this.orderItems = new ArrayList<>();
        this.totalAmount = 0;
        this.taxAmount = 0;
        this.discountAmount = 0;
        this.serviceCharge = 0;
        this.finalAmount = 0;
    }
    
    public Order(Customer customer, Cashier cashier) {
        this();
        this.customer = customer;
        this.cashier = cashier;
    }
    
    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }
    
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderDate() {
        return orderDate;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public double getTaxAmount() {
        return taxAmount;
    }
    
    public double getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
        calculateTotal();
    }
    
    public double getServiceCharge() {
        return serviceCharge;
    }
    
    public double getFinalAmount() {
        return finalAmount;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    
    public Cashier getCashier() {
        return cashier;
    }
    
    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    
    public Payment getPayment() {
        return payment;
    }
    
    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    
    public String getSpecialInstructions() {
        return specialInstructions;
    }
    
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
    
    // Business Methods
    public void addOrderItem(OrderItem item) {
        orderItems.add(item);
        calculateTotal();
    }
    
    public void removeOrderItem(int index) {
        if (index >= 0 && index < orderItems.size()) {
            orderItems.remove(index);
            calculateTotal();
        }
    }
    
    public void updateQuantity(int itemId, int newQuantity) {
        for (OrderItem item : orderItems) {
            if (item.getMenuItem().getItemId() == itemId) {
                item.setQuantity(newQuantity);
                break;
            }
        }
        calculateTotal();
    }
    
    public void calculateTotal() {
        // Calculate subtotal from items
        totalAmount = 0;
        for (OrderItem item : orderItems) {
            totalAmount += item.getSubtotal();
        }
        
        // Calculate tax
        taxAmount = totalAmount * TAX_RATE;
        
        // Calculate service charge
        serviceCharge = totalAmount * SERVICE_RATE;
        
        // Calculate final amount
        finalAmount = totalAmount + taxAmount + serviceCharge - discountAmount;
        
        // Apply customer discount if available
        if (customer != null) {
            double customerDiscount = totalAmount * customer.getDiscountRate();
            discountAmount += customerDiscount;
            finalAmount -= customerDiscount;
        }
    }
    
    public void displayOrder() {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                     ORDER DETAILS                      ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Order ID: %-50d║\n", orderId);
        System.out.printf("║ Date: %-52s║\n", orderDate);
        System.out.printf("║ Status: %-50s║\n", status);
        if (customer != null) {
            System.out.printf("║ Customer: %-48s║\n", customer.getFullName());
        }
        if (cashier != null) {
            System.out.printf("║ Cashier: %-49s║\n", cashier.getFullName());
        }
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║ ITEMS:                                                 ║");
        
        for (OrderItem item : orderItems) {
            System.out.printf("║   %-2d x %-30s $%-10.2f║\n", 
                item.getQuantity(),
                item.getMenuItem().getItemName().length() > 30 ? 
                    item.getMenuItem().getItemName().substring(0, 27) + "..." : 
                    item.getMenuItem().getItemName(),
                item.getSubtotal());
        }
        
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Subtotal: %-48.2f║\n", totalAmount);
        System.out.printf("║ Tax (10%%): %-47.2f║\n", taxAmount);
        System.out.printf("║ Service (5%%): %-45.2f║\n", serviceCharge);
        System.out.printf("║ Discount: %-48.2f║\n", discountAmount);
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ TOTAL: $%-51.2f║\n", finalAmount);
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(orderId).append("\n");
        sb.append("Date: ").append(orderDate).append("\n");
        sb.append("Status: ").append(status).append("\n");
        sb.append("----------------------------------------\n");
        
        for (OrderItem item : orderItems) {
            sb.append(String.format("%d x %s = $%.2f\n", 
                item.getQuantity(),
                item.getMenuItem().getItemName(),
                item.getSubtotal()));
        }
        
        sb.append("----------------------------------------\n");
        sb.append(String.format("Total: $%.2f\n", finalAmount));
        
        return sb.toString();
    }
    
    public String getInvoice() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("╔════════════════════════════════════════════════════════════════╗\n");
        sb.append("║                      CAFETERIA INVOICE                         ║\n");
        sb.append("╠════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ Invoice #: %-56s║\n", "INV-" + orderId));
        sb.append(String.format("║ Date: %-59s║\n", orderDate));
        sb.append("╠════════════════════════════════════════════════════════════════╣\n");
        sb.append("║ ITEM                          QTY    PRICE     TOTAL          ║\n");
        sb.append("║────────────────────────────────────────────────────────────────║\n");
        
        for (OrderItem item : orderItems) {
            String itemName = item.getMenuItem().getItemName();
            if (itemName.length() > 28) itemName = itemName.substring(0, 25) + "...";
            sb.append(String.format("║ %-28s %3d   $%-7.2f $%-8.2f║\n",
                itemName,
                item.getQuantity(),
                item.getUnitPrice(),
                item.getSubtotal()));
        }
        
        sb.append("╠════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-41s $%-10.2f║\n", "Subtotal:", totalAmount));
        sb.append(String.format("║ %-41s $%-10.2f║\n", "Tax (10%):", taxAmount));
        sb.append(String.format("║ %-41s $%-10.2f║\n", "Service Charge (5%):", serviceCharge));
        if (discountAmount > 0) {
            sb.append(String.format("║ %-41s $%-10.2f║\n", "Discount:", discountAmount));
        }
        sb.append("╠════════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║ %-41s $%-10.2f║\n", "TOTAL AMOUNT:", finalAmount));
        sb.append("╚════════════════════════════════════════════════════════════════╝\n");
        sb.append("\n              Thank you for your order!\n");
        sb.append("                  Visit Again!\n\n");
        
        return sb.toString();
    }
    
    public boolean isCompleted() {
        return "COMPLETED".equals(status);
    }
    
    public boolean isCancelled() {
        return "CANCELLED".equals(status);
    }
    
    public void cancel() {
        this.status = "CANCELLED";
        System.out.println("Order #" + orderId + " has been cancelled.");
    }
    
    @Override
    public String toString() {
        return String.format("Order #%d - %s - $%.2f", orderId, status, finalAmount);
    }
}