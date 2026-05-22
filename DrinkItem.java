package cafeteria.menu;

public class DrinkItem extends MenuItem {
    private static final long serialVersionUID = 1L;
    
    // Attributes
    private String size; // SMALL, MEDIUM, LARGE, EXTRA_LARGE
    private String temperature; // HOT, COLD, ICED, ROOM_TEMPERATURE
    private String brand;
    private boolean isCarbonated;
    private int caffeineContent; // in mg
    private boolean hasSugar;
    private String servingSuggestion;
    
    // Constructors
    public DrinkItem() {
        super();
        this.size = "MEDIUM";
        this.temperature = "COLD";
        this.isCarbonated = false;
        this.hasSugar = true;
    }
    
    public DrinkItem(String itemName, String description, double price, int calories, 
                     int preparationTime, String size, String temperature, String brand) {
        super(itemName, description, price, calories, preparationTime);
        this.size = size;
        this.temperature = temperature;
        this.brand = brand;
        this.isCarbonated = false;
        this.hasSugar = true;
    }
    
    public DrinkItem(String itemName, String description, double price, int calories,
                     int preparationTime, String size, String temperature, 
                     String brand, boolean isCarbonated, int caffeineContent) {
        this(itemName, description, price, calories, preparationTime, size, temperature, brand);
        this.isCarbonated = isCarbonated;
        this.caffeineContent = caffeineContent;
    }
    
    // Getters and Setters
    public String getSize() {
        return size;
    }
    
    public void setSize(String size) {
        this.size = size;
        // Adjust price based on size
        switch (size.toUpperCase()) {
            case "SMALL":
                this.price = originalPrice * 0.8;
                break;
            case "LARGE":
                this.price = originalPrice * 1.3;
                break;
            case "EXTRA_LARGE":
                this.price = originalPrice * 1.6;
                break;
            default:
                this.price = originalPrice;
        }
    }
    
    private double originalPrice;
    
    public void setOriginalPrice(double price) {
        this.originalPrice = price;
        setSize(this.size); // Recalculate price
    }
    
    public String getTemperature() {
        return temperature;
    }
    
    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public boolean isCarbonated() {
        return isCarbonated;
    }
    
    public void setCarbonated(boolean carbonated) {
        isCarbonated = carbonated;
    }
    
    public int getCaffeineContent() {
        return caffeineContent;
    }
    
    public void setCaffeineContent(int caffeineContent) {
        this.caffeineContent = caffeineContent;
    }
    
    public boolean hasSugar() {
        return hasSugar;
    }
    
    public void setHasSugar(boolean hasSugar) {
        this.hasSugar = hasSugar;
    }
    
    public String getServingSuggestion() {
        return servingSuggestion;
    }
    
    public void setServingSuggestion(String servingSuggestion) {
        this.servingSuggestion = servingSuggestion;
    }
    
    // Business Methods
    public void displayDrink() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║           DRINK ITEM DETAILS           ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf("║ Drink: %-34s║\n", itemName);
        System.out.printf("║ Brand: %-34s║\n", brand);
        System.out.printf("║ Size: %-35s║\n", size);
        System.out.printf("║ Temperature: %-28s║\n", temperature);
        System.out.printf("║ Carbonated: %-29s║\n", isCarbonated ? "Yes" : "No");
        System.out.printf("║ Caffeine: %-31d mg║\n", caffeineContent);
        System.out.printf("║ Sugar: %-34s║\n", hasSugar ? "Yes" : "No");
        System.out.printf("║ Price: $%-33.2f║\n", price);
        System.out.printf("║ Calories: %-31d║\n", calories);
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    public String getServingSuggestions() {
        if (servingSuggestion != null && !servingSuggestion.isEmpty()) {
            return servingSuggestion;
        }
        
        if ("HOT".equalsIgnoreCase(temperature)) {
            return "Best served hot. Perfect for cold days.";
        } else if ("COLD".equalsIgnoreCase(temperature) || "ICED".equalsIgnoreCase(temperature)) {
            return "Serve with ice for maximum refreshment.";
        }
        return "Enjoy as is.";
    }
    
    public boolean isDecaffeinated() {
        return caffeineContent == 0;
    }
    
    public boolean isDietFriendly() {
        return !hasSugar && calories < 100;
    }
    
    @Override
    public String getItemType() {
        return "DRINK";
    }
    
    @Override
    public String getDietaryInfo() {
        return String.format("%s | %s | %s", 
            temperature,
            size,
            isCarbonated ? "Carbonated" : "Non-Carbonated");
    }
}