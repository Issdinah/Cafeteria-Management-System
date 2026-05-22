package cafeteria.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Category implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1;
    
    // Attributes
    private int categoryId;
    private String categoryName;
    private String description;
    private int itemCount;
    private List<MenuItem> items;
    private String imagePath;
    private boolean isActive;
    
    // Constructors
    public Category() {
        this.categoryId = ++nextId;
        this.items = new ArrayList<>();
        this.itemCount = 0;
        this.isActive = true;
    }
    
    public Category(String categoryName, String description) {
        this();
        this.categoryName = categoryName;
        this.description = description;
    }
    
    public Category(String categoryName, String description, String imagePath) {
        this(categoryName, description);
        this.imagePath = imagePath;
    }
    
    // Getters and Setters
    public int getCategoryId() {
        return categoryId;
    }
    
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryName() {
        return categoryName;
    }
    
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getItemCount() {
        return itemCount;
    }
    
    public List<MenuItem> getItems() {
        return items;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public boolean isActive() {
        return isActive;
    }
    
    public void setActive(boolean active) {
        isActive = active;
    }
    
    // Business Methods
    public void displayCategory() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║             CATEGORY INFO              ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Category ID: %-28d║\n", categoryId);
        System.out.printf("║ Name: %-35s║\n", categoryName);
        System.out.printf("║ Description: %-29s║\n", description);
        System.out.printf("║ Items: %-34d║\n", itemCount);
        System.out.printf("║ Status: %-33s║\n", isActive ? "ACTIVE" : "INACTIVE");
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    public void updateCategory(String newName, String newDescription) {
        this.categoryName = newName;
        this.description = newDescription;
        System.out.println("Category updated: " + categoryName);
    }
    
    public void addItem(MenuItem item) {
        if (!items.contains(item)) {
            items.add(item);
            itemCount++;
            System.out.println("Item '" + item.getItemName() + "' added to category '" + categoryName + "'");
        }
    }
    
    public void removeItem(MenuItem item) {
        if (items.remove(item)) {
            itemCount--;
            System.out.println("Item removed from category '" + categoryName + "'");
        }
    }
    
    public void removeItem(int itemId) {
        items.removeIf(item -> item.getItemId() == itemId);
        itemCount = items.size();
        System.out.println("Item ID " + itemId + " removed from category");
    }
    
    public List<MenuItem> getAvailableItems() {
        List<MenuItem> available = new ArrayList<>();
        for (MenuItem item : items) {
            if (item.isAvailable()) {
                available.add(item);
            }
        }
        return available;
    }
    
    public List<MenuItem> searchItemByName(String searchTerm) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : items) {
            if (item.getItemName().toLowerCase().contains(searchTerm.toLowerCase())) {
                results.add(item);
            }
        }
        return results;
    }
    
    public List<MenuItem> getItemsByPriceRange(double min, double max) {
        List<MenuItem> results = new ArrayList<>();
        for (MenuItem item : items) {
            if (item.getPrice() >= min && item.getPrice() <= max) {
                results.add(item);
            }
        }
        return results;
    }
    
    public double getAverageItemPrice() {
        if (items.isEmpty()) return 0;
        double sum = 0;
        for (MenuItem item : items) {
            sum += item.getPrice();
        }
        return sum / items.size();
    }
    
    public MenuItem getMostPopularItem() {
        if (items.isEmpty()) return null;
        MenuItem popular = items.get(0);
        for (MenuItem item : items) {
            if (item.getTimesOrdered() > popular.getTimesOrdered()) {
                popular = item;
            }
        }
        return popular;
    }
    
    public void displayAllItems() {
        System.out.println("\n=== Category: " + categoryName + " ===");
        System.out.println("Description: " + description);
        System.out.println("Items available: " + itemCount);
        System.out.println("----------------------------------------");
        for (MenuItem item : items) {
            System.out.printf("%-30s $%-8.2f %s\n", 
                item.getItemName(), 
                item.getPrice(),
                item.isAvailable() ? "✓" : "✗");
        }
        System.out.println("----------------------------------------\n");
    }
    
    @Override
    public String toString() {
        return String.format("%s (%d items)", categoryName, itemCount);
    }
}