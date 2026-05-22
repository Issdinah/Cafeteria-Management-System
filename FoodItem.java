package cafeteria.menu;

import java.util.ArrayList;
import java.util.List;

public class FoodItem extends MenuItem {
    private static final long serialVersionUID = 1L;
    
    // Attributes
    private String foodType; // VEG, NON_VEG, EGG
    private String spicyLevel; // MILD, MEDIUM, HOT, EXTRA_HOT
    private boolean isVegetarian;
    private String cuisineType; // ITALIAN, MEXICAN, CHINESE, INDIAN, AMERICAN
    private String preparationMethod; // GRILLED, FRIED, BAKED, STEAMED
    private List<String> ingredients;
    private List<String> allergens;
    
    // Constructors
    public FoodItem() {
        super();
        this.ingredients = new ArrayList<>();
        this.allergens = new ArrayList<>();
        this.isVegetarian = false;
        this.spicyLevel = "MEDIUM";
    }
    
    public FoodItem(String itemName, String description, double price, int calories, 
                    int preparationTime, String foodType, String spicyLevel, String cuisineType) {
        super(itemName, description, price, calories, preparationTime);
        this.foodType = foodType;
        this.spicyLevel = spicyLevel;
        this.cuisineType = cuisineType;
        this.isVegetarian = "VEG".equalsIgnoreCase(foodType);
        this.ingredients = new ArrayList<>();
        this.allergens = new ArrayList<>();
    }
    
    // Getters and Setters
    public String getFoodType() {
        return foodType;
    }
    
    public void setFoodType(String foodType) {
        this.foodType = foodType;
        this.isVegetarian = "VEG".equalsIgnoreCase(foodType);
    }
    
    public String getSpicyLevel() {
        return spicyLevel;
    }
    
    public void setSpicyLevel(String spicyLevel) {
        this.spicyLevel = spicyLevel;
    }
    
    public boolean isVegetarian() {
        return isVegetarian;
    }
    
    public String getCuisineType() {
        return cuisineType;
    }
    
    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
    
    public String getPreparationMethod() {
        return preparationMethod;
    }
    
    public void setPreparationMethod(String preparationMethod) {
        this.preparationMethod = preparationMethod;
    }
    
    public List<String> getIngredients() {
        return ingredients;
    }
    
    public void addIngredient(String ingredient) {
        ingredients.add(ingredient);
    }
    
    public void removeIngredient(String ingredient) {
        ingredients.remove(ingredient);
    }
    
    public List<String> getAllergens() {
        return allergens;
    }
    
    public void addAllergen(String allergen) {
        allergens.add(allergen);
    }
    
    // Business Methods
    public void displayFood() {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║            FOOD ITEM DETAILS           ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.printf ("║ Item: %-35s║\n", itemName);
        System.out.printf ("║ Type: %-35s║\n", foodType);
        System.out.printf ("║ Cuisine: %-32s║\n", cuisineType);
        System.out.printf ("║ Spicy Level: %-28s║\n", spicyLevel);
        System.out.printf ("║ Preparation: %-29s║\n", preparationMethod);
        System.out.printf ("║ Price: $%-33.2f║\n", price);
        System.out.printf ("║ Calories: %-31d║\n", calories);
        System.out.printf ("║ Time: %-35d min║\n", preparationTime);
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Ingredients:                           ║");
        for (String ingredient : ingredients) {
            System.out.printf("║   • %-36s║\n", ingredient);
        }
        if (!allergens.isEmpty()) {
            System.out.println("╠════════════════════════════════════════╣");
            System.out.println("║ Allergens:                             ║");
            for (String allergen : allergens) {
                System.out.printf("║   ⚠ %-36s║\n", allergen);
            }
        }
        System.out.println("╚════════════════════════════════════════╝");
    }
    
    public String getNutritionInfo() {
        return String.format(
            "Nutrition Info for %s:\n" +
            "- Calories: %d\n" +
            "- Food Type: %s\n" +
            "- Vegetarian: %s\n" +
            "- Spice Level: %s",
            itemName, calories, foodType, isVegetarian ? "Yes" : "No", spicyLevel
        );
    }
    
    public boolean hasAllergen(String allergen) {
        return allergens.contains(allergen);
    }
    
    @Override
    public String getItemType() {
        return "FOOD";
    }
    
    @Override
    public String getDietaryInfo() {
        return String.format("%s | %s | %s", 
            isVegetarian ? "Vegetarian" : "Non-Vegetarian",
            cuisineType,
            spicyLevel + " Spice");
    }
}