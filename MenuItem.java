package cafeteria.menu;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 100;
    
    // Attributes
    protected int itemId;
    protected String itemName;
    protected String description;
    protected double price;
    protected String imagePath;
    protected int calories;
    protected int preparationTime; // in minutes
    protected boolean isAvailable;
    protected double rating;
    protected int timesOrdered;
    
    // Constructors
    public MenuItem() {
        this.itemId = ++nextId;
        this.isAvailable = true;
        this.rating = 0;
        this.timesOrdered = 0;
    }
    
    public MenuItem(String itemName, String description, double price, int calories, int preparationTime) {
        this();
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.calories = calories;
        this.preparationTime = preparationTime;
    }
    
    public MenuItem(String itemName, String description, double price, String imagePath, int calories, int preparationTime) {
        this(itemName, description, price, calories, preparationTime);
        this.imagePath = imagePath;
    }
    
    // Getters and Setters
    public int getItemId() {
        return itemId;
    }
    
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public double getPrice() {
        return price;
    }
    
    public void setPrice(double price) {
        this.price = price;
    }
    
    public String getImagePath() {
        return imagePath;
    }
    
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public int getCalories() {
        return calories;
    }
    
    public void setCalories(int calories) {
        this.calories = calories;
    }
    
    public int getPreparationTime() {
        return preparationTime;
    }
    
    public void setPreparationTime(int preparationTime) {
        this.preparationTime = preparationTime;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public double getRating() {
        return rating;
    }
    
    public void setRating(double rating) {
        this.rating = rating;
    }
    
    public int getTimesOrdered() {
        return timesOrdered;
    }
    
    public void setTimesOrdered(int timesOrdered) {
        this.timesOrdered = timesOrdered;
    }
    
    // Business Methods
    public void displayItem() {
        System.out.printf("%-25s $%-8.2f %s\n", itemName, price, description);
    }
    
    public void updatePrice(double newPrice) {
        if (newPrice > 0) {
            this.price = newPrice;
            System.out.println("Price updated for " + itemName + ": $" + newPrice);
        }
    }
    
    public void updateAvailability() {
        this.isAvailable = !this.isAvailable;
        System.out.println(itemName + " is now " + (isAvailable ? "available" : "unavailable"));
    }
    
    public void incrementTimesOrdered() {
        this.timesOrdered++;
    }
    
    public void updateRating(double newRating) {
        if (newRating >= 0 && newRating <= 5) {
            this.rating = newRating;
        }
    }
    
    public abstract String getItemType();
    public abstract String getDietaryInfo();
    
    @Override
    public String toString() {
        return String.format("%s - $%.2f (%s)", itemName, price, getItemType());
    }
}