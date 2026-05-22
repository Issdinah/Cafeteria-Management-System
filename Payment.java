package cafeteria.order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Payment {
    private static int nextId = 2000;
    
    // Attributes
    private int paymentId;
    private String paymentType; // CASH, CARD, MOBILE
    private double amount;
    private double tipAmount;
    private String paymentDate;
    private String transactionId;
    private boolean isCompleted;
    private String cardNumber; // For card payments (masked)
    private String cardType; // VISA, MASTERCARD, AMEX
    private String mobileProvider; // For mobile payments
    
    // Constructors
    public Payment() {
        this.paymentId = ++nextId;
        this.paymentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        this.transactionId = generateTransactionId();
        this.isCompleted = false;
        this.tipAmount = 0;
    }
    
    public Payment(String paymentType, double amount) {
        this();
        this.paymentType = paymentType;
        this.amount = amount;
    }
    
    public Payment(String paymentType, double amount, double tipAmount) {
        this(paymentType, amount);
        this.tipAmount = tipAmount;
        this.amount += tipAmount;
    }
    
    // Generate unique transaction ID
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 5).toUpperCase();
    }
    
    // Getters and Setters
    public int getPaymentId() {
        return paymentId;
    }
    
    public String getPaymentType() {
        return paymentType;
    }
    
    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
    
    public double getTipAmount() {
        return tipAmount;
    }
    
    public void setTipAmount(double tipAmount) {
        this.tipAmount = tipAmount;
    }
    
    public String getPaymentDate() {
        return paymentDate;
    }
    
    public String getTransactionId() {
        return transactionId;
    }
    
    public boolean isCompleted() {
        return isCompleted;
    }
    
    public String getCardNumber() {
        return cardNumber;
    }
    
    public void setCardNumber(String cardNumber) {
        // Mask card number for security
        if (cardNumber != null && cardNumber.length() >= 4) {
            this.cardNumber = "****-****-****-" + cardNumber.substring(cardNumber.length() - 4);
        } else {
            this.cardNumber = cardNumber;
        }
    }
    
    public String getCardType() {
        return cardType;
    }
    
    public void setCardType(String cardType) {
        this.cardType = cardType;
    }
    
    public String getMobileProvider() {
        return mobileProvider;
    }
    
    public void setMobileProvider(String mobileProvider) {
        this.mobileProvider = mobileProvider;
    }
    
    // Business Methods
    public boolean makePayment() {
        // Simulate payment processing
        System.out.println("Processing " + paymentType + " payment...");
        
        // Validation logic based on payment type
        boolean success = validatePayment();
        
        if (success) {
            isCompleted = true;
            System.out.println("Payment successful! Transaction ID: " + transactionId);
            System.out.println("Amount: $" + amount);
            if (tipAmount > 0) {
                System.out.println("Tip included: $" + tipAmount);
            }
        } else {
            System.out.println("Payment failed. Please try again.");
        }
        
        return success;
    }
    
    private boolean validatePayment() {
        switch (paymentType.toUpperCase()) {
            case "CASH":
                return amount > 0;
            case "CARD":
                return cardNumber != null && !cardNumber.isEmpty() && cardType != null;
            case "MOBILE":
                return mobileProvider != null && !mobileProvider.isEmpty();
            default:
                return false;
        }
    }
    
    public boolean processCardPayment(String cardNumber, String expiry, String cvv) {
        setCardNumber(cardNumber);
        // Simulate card validation
        if (cardNumber != null && cardNumber.length() >= 16) {
            // Determine card type
            if (cardNumber.startsWith("4")) {
                cardType = "VISA";
            } else if (cardNumber.startsWith("5")) {
                cardType = "MASTERCARD";
            } else if (cardNumber.startsWith("3")) {
                cardType = "AMEX";
            } else {
                cardType = "UNKNOWN";
            }
            return makePayment();
        }
        return false;
    }
    
    public boolean processCashPayment(double cashReceived) {
        if (cashReceived >= amount) {
            double change = cashReceived - amount;
            System.out.println("Cash received: $" + cashReceived);
            System.out.println("Change to return: $" + change);
            return makePayment();
        }
        System.out.println("Insufficient cash. Need: $" + (amount - cashReceived));
        return false;
    }
    
    public boolean processMobilePayment(String provider, String phoneNumber) {
        this.mobileProvider = provider;
        System.out.println("Processing " + provider + " payment for " + phoneNumber);
        return makePayment();
    }
    
    public void printReceipt() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║                    PAYMENT RECEIPT                     ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Payment ID: %-48d║\n", paymentId);
        System.out.printf("║ Transaction ID: %-44s║\n", transactionId);
        System.out.printf("║ Date: %-53s║\n", paymentDate);
        System.out.printf("║ Payment Type: %-45s║\n", paymentType);
        
        if ("CARD".equalsIgnoreCase(paymentType) && cardNumber != null) {
            System.out.printf("║ Card: %-53s║\n", cardNumber);
            System.out.printf("║ Card Type: %-48s║\n", cardType);
        } else if ("MOBILE".equalsIgnoreCase(paymentType)) {
            System.out.printf("║ Provider: %-49s║\n", mobileProvider);
        }
        
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.printf("║ Amount: $%-51.2f║\n", amount - tipAmount);
        if (tipAmount > 0) {
            System.out.printf("║ Tip: $%-54.2f║\n", tipAmount);
        }
        System.out.printf("║ Total: $%-52.2f║\n", amount);
        System.out.printf("║ Status: %-51s║\n", isCompleted ? "COMPLETED" : "PENDING");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println("\n              Thank you for your payment!\n");
    }
    
    public void refund() {
        if (isCompleted) {
            isCompleted = false;
            System.out.println("Refund processed for transaction: " + transactionId);
            System.out.println("Amount refunded: $" + amount);
        } else {
            System.out.println("Cannot refund - payment not completed");
        }
    }
    
    public String getPaymentStatus() {
        return isCompleted ? "COMPLETED" : "PENDING";
    }
    
    @Override
    public String toString() {
        return String.format("Payment #%d - %s - $%.2f