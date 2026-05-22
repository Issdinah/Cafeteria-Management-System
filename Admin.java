package cafeteria.person;

import cafeteria.menu.MenuItem;
import cafeteria.menu.Category;
import cafeteria.database.DatabaseManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Admin extends Person {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;
    
    // Attributes
    private int adminId;
    private String username;
    private String password;
    private String role;
    private String lastLogin;
    
    // Constructors
    public Admin() {
        super();
        this.adminId = ++nextId;
        this.role = "STAFF";
        this.lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    public Admin(String fullName, String phone, String email, String username, String password) {
        super(fullName, phone, email);
        this.adminId = ++nextId;
        this.username = username;
        this.password = password;
        this.role = "STAFF";
        this.lastLogin = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
    
    public Admin(String fullName, String phone, String email, String username, String password, String role) {
        this(fullName, phone, email, username, password);
        this.role = role;
    }
    
    // Getters and Setters
    public int getAdminId() {
        return adminId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    // Authentication method
    public boolean authenticate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    
    // ============ MENU ITEM MANAGEMENT ============
    
    public void addMenuItem(MenuItem item) {
        DatabaseManager.getInstance().addMenuItem(item);
        System.out.println("Menu item added: " + item.getItemName());
    }
    
    public void updateMenuItem(int itemId, MenuItem newItem) {
        DatabaseManager.getInstance().updateMenuItem(itemId, newItem);
        System.out.println("Menu item updated: ID " + itemId);
    }
    
    public void deleteMenuItem(int itemId) {
        DatabaseManager.getInstance().deleteMenuItem(itemId);
        System.out.println("Menu item deleted: ID " + itemId);
    }
    
    public MenuItem getMenuItem(int id) {
        return DatabaseManager.getInstance().getMenuItem(id);
    }
    
    public List<MenuItem> getAllMenuItems() {
        return DatabaseManager.getInstance().getAllMenuItems();
    }
    
    // ============ CATEGORY MANAGEMENT ============
    
    public void addCategory(Category category) {
        DatabaseManager.getInstance().addCategory(category);
        System.out.println("Category added: " + category.getCategoryName());
    }
    
    public void updateCategory(int categoryId, Category newCategory) {
        DatabaseManager.getInstance().updateCategory(categoryId, newCategory);
        System.out.println("Category updated: ID " + categoryId);
    }
    
    public void deleteCategory(int categoryId) {
        DatabaseManager.getInstance().deleteCategory(categoryId);
        System.out.println("Category deleted: ID " + categoryId);
    }
    
    public Category getCategory(int id) {
        return DatabaseManager.getInstance().getCategory(id);
    }
    
    // ============ CUSTOMER MANAGEMENT ============
    
    public void addCustomer(Customer customer) {
        DatabaseManager.getInstance().addCustomer(customer);
        System.out.println("Customer added: " + customer.getFullName());
    }
    
    public void updateCustomer(int customerId, Customer customer) {
        DatabaseManager.getInstance().updateCustomer(customerId, customer);
        System.out.println("Customer updated: ID " + customerId);
    }
    
    public void deleteCustomer(int customerId) {
        DatabaseManager.getInstance().deleteCustomer(customerId);
        System.out.println("Customer deleted: ID " + customerId);
    }
    
    public List<Customer> getAllCustomers() {
        return DatabaseManager.getInstance().getAllCustomers();
    }
    
    // ============ SIMPLE REPORTS (NO PERCENTAGES) ============
    
    public void generateSalesReport(String startDate, String endDate) {
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                   SALES REPORT                         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Period: %s to %s║\n", startDate, endDate);
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Total Orders: %d║\n", getTotalOrders());
        System.out.printf("║ Total Revenue: $%.2f║\n", getTotalRevenue());
        System.out.printf("║ Average Order: $%.2f║\n", getAverageOrderValue());
        System.out.printf("║ Top Category: %s║\n", getTopCategory());
        System.out.printf("║ Best Seller: %s║\n", getBestSeller());
        System.out.println("╚════════════════════════════════════════════════════════╝");
    }
    
    public void viewAllItems() {
        System.out.println("\n========== ALL MENU ITEMS ==========");
        List<MenuItem> items = DatabaseManager.getInstance().getAllMenuItems();
        for (MenuItem item : items) {
            System.out.printf("ID: %d | %s | $%.2f | Available: %s\n",
                item.getItemId(),
                item.getItemName(),
                item.getPrice(),
                item.isAvailable() ? "Yes" : "No");
        }
        System.out.println("=====================================\n");
    }
    
    public void viewAllCategories() {
        System.out.println("\n========== ALL CATEGORIES ==========");
        List<Category> categories = DatabaseManager.getInstance().getAllCategories();
        for (Category cat : categories) {
            System.out.printf("ID: %d | %s | Items: %d\n",
                cat.getCategoryId(),
                cat.getCategoryName(),
                cat.getItemCount());
        }
        System.out.println("=====================================\n");
    }
    
    public void viewAllCustomers() {
        System.out.println("\n========== ALL CUSTOMERS ==========");
        List<Customer> customers = DatabaseManager.getInstance().getAllCustomers();
        for (Customer cust : customers) {
            System.out.printf("ID: %d | %s | Phone: %s | Visits: %d\n",
                cust.getCustomerId(),
                cust.getFullName(),
                cust.getPhone(),
                cust.getVisitCount());
        }
        System.out.println("====================================\n");
    }
    
    public void getStatistics() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         SYSTEM STATISTICS              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Total Customers: %d║\n", getTotalCustomers());
        System.out.printf("║ Total Orders: %d║\n", getTotalOrders());
        System.out.printf("║ Total Revenue: $%.2f║\n", getTotalRevenue());
        System.out.printf("║ Active Menu Items: %d║\n", getActiveMenuItemCount());
        System.out.printf("║ Total Categories: %d║\n", getTotalCategories());
        System.out.println("╚════════════════════════════════════════╝\n");
    }
    
    // ============ HELPER METHODS FOR REPORTS ============
    
    private int getTotalOrders() {
        return DatabaseManager.getInstance().getTotalOrders();
    }
    
    private double getTotalRevenue() {
        return DatabaseManager.getInstance().getTotalRevenue();
    }
    
    private double getAverageOrderValue() {
        int totalOrders = getTotalOrders();
        if (totalOrders == 0) return 0;
        return getTotalRevenue() / totalOrders;
    }
    
    private int getTotalCustomers() {
        return DatabaseManager.getInstance().getTotalCustomers();
    }
    
    private int getActiveMenuItemCount() {
        return DatabaseManager.getInstance().getActiveMenuItemCount();
    }
    
    private int getTotalCategories() {
        return DatabaseManager.getInstance().getAllCategories().size();
    }
    
    private String getTopCategory() {
        // Simple implementation - returns first category
        List<Category> cats = DatabaseManager.getInstance().getAllCategories();
        if (cats.isEmpty()) return "None";
        return cats.get(0).getCategoryName();
    }
    
    private String getBestSeller() {
        List<MenuItem> items = DatabaseManager.getInstance().getAllMenuItems();
        if (items.isEmpty()) return "None";
        
        MenuItem best = items.get(0);
        for (MenuItem item : items) {
            if (item.getTimesOrdered() > best.getTimesOrdered()) {
                best = item;
            }
        }
        return best.getItemName();
    }
    
    // ============ DISPLAY METHODS ============
    
    @Override
    public void displayInfo() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║            ADMIN INFORMATION           ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Admin ID: %d║\n", adminId);
        System.out.printf("║ Name: %s║\n", fullName);
        System.out.printf("║ Username: %s║\n", username);
        System.out.printf("║ Role: %s║\n", role);
        System.out.printf("║ Last Login: %s║\n", lastLogin);
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    @Override
    public String getRole() {
        return "ADMIN";
    }
    
    @Override
    public String toString() {
        return String.format("[%s] %s - %s", username, fullName, role);
    }
}