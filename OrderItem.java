package cafeteria.order;

import cafeteria.menu.MenuItem;
import java.io.Serializable;

public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;
    
    // Attributes
    private int orderItemId;
    private int quantity;
    private double subtotal;
    private double unitPrice;
    private double discountPerItem;
    private MenuItem menuItem;
    private String specialInstructions;
    
    // Constructors
    public OrderItem() {
        this.orderItemId = ++nextId;
        this.quantity = 1;
        this.discountPerItem = 0;
    }
    
    public OrderItem(MenuItem menuItem, int quantity) {
        this();
        this.menuItem = menuItem;
        this.quantity = quantity;
        this.unitPrice = menuItem.getPrice();
        calculateSubtotal();
    }
    
    public OrderItem(MenuItem menuItem, int quantity, String specialInstructions) {
        this(menuItem, quantity);
        this.specialInstructions = specialInstructions;
    }
    
    // Getters and Setters
    public int getOrderItemId() {
        return orderItemId;
    }
    
    public int getQuantity() {
        return quantity;
    }
    
    public void setQuantity(int quantity) {
        if (quantity > 0) {
            this.quantity = quantity;
            calculateSubtotal();
        }
    }
    
    public double getSubtotal() {
        return subtotal;
    }
    
    public double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        calculateSubtotal();
    }
    
    public double getDiscountPerItem() {
        return discountPerItem;
    }
    
    public void setDiscountPerItem(double discountPerItem) {
        this.discountPerItem = discountPerItem;
        calculateSubtotal();
    }
    
    public MenuItem getMenuItem() {
        return menuItem;
    }
    
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.unitPrice = menuItem.getPrice();
        calculateSubtotal();
    }
    
    public String getSpecialInstructions() {
        return specialInstructions;
    }
    
    public void setSpecialInstructions(String specialInstructions) {
        this.specialInstructions = specialInstructions;
    }
    
    // Business Methods
    public void calculateSubtotal() {
        double itemTotal = unitPrice * quantity;
        double itemDiscount = discountPerItem * quantity;
        this.subtotal = itemTotal - itemDiscount;
    }
    
    public void applyItemDiscount(double discountPercentage) {
        if (discountPercentage > 0 && discountPercentage <= 100) {
            this.discountPerItem = unitPrice * (discountPercentage / 100);
            calculateSubtotal();
        }
    }
    
    public String getFormattedDetails() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%d x %s", quantity, menuItem.getItemName()));
        if (specialInstructions != null && !specialInstructions.isEmpty()) {
            sb.append("\n  Note: ").append(specialInstructions);
        }
        sb.append(String.format("\n  $%.2f each → Subtotal: $%.2f", unitPrice, subtotal));
        return sb.toString();
    }
    
    @Override
    public String toString() {
        return String.format("%d x %s = $%.2f", quantity, menuItem.getItemName(), subtotal);
    }
}