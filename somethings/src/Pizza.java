import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.Period;

class SliceoHeaven {
    private String storeName;
    private String storeAddress;
    private List<String> pizzaIngredients;
    private double pizzaPrice;
    private List<String> sides;
    private List<String> drinks;
    private Map<String, Double> sidePrices;
    private Map<String, Double> drinkPrices;
    private String orderID;
    private double orderTotal;

    private static final String DEF_ORDER_ID = "DEF-SOH-099";
    private static final String DEF_PIZZA_INGREDIENTS = "Mozzarella Cheese";
    private static final double DEF_ORDER_TOTAL = 15.00;
    private static final long BLACKLISTED_NUMBER = 12345678901234L; 

    public SliceoHeaven() {
        this.orderID = DEF_ORDER_ID;
        this.pizzaIngredients = new ArrayList<>();
        this.pizzaIngredients.add(DEF_PIZZA_INGREDIENTS);
        this.orderTotal = DEF_ORDER_TOTAL;
        this.sides = new ArrayList<>();
        this.drinks = new ArrayList<>();
    }

    public SliceoHeaven(String name, String address, String email, String phone, String menu, List<String> ingredients, double price,
                        List<String> sideItems, Map<String, Double> sideItemPrices, List<String> drinkItems, Map<String, Double> drinkItemPrices) {
        this.storeName = name;
        this.storeAddress = address;
        this.pizzaIngredients = ingredients;
        this.pizzaPrice = price;
        this.sides = sideItems;
        this.sidePrices = sideItemPrices;
        this.drinks = drinkItems;
        this.drinkPrices = drinkItemPrices;
        this.orderID = DEF_ORDER_ID;
        this.orderTotal = 0;
    }

    public SliceoHeaven(String orderID, List<String> pizzaIngredients, double orderTotal) {
        this.orderID = orderID;
        this.pizzaIngredients = pizzaIngredients;
        this.orderTotal = orderTotal;
    }
    public void takeOrder() {
        try (Scanner scanner = new Scanner(System.in)) {
            int[] choices = new int[3];
            boolean valid;
            do {
                valid = true;
                System.out.println("Please pick any three of the following ingredients:");
                System.out.println("1. Mushroom");
                System.out.println("2. Paprika");
                System.out.println("3. Sun-dried tomatoes");
                System.out.println("4. Chicken");
                System.out.println("5. Pineapple");
                System.out.print("Enter any three choices (1, 2, 3,… separated by spaces): ");
                String input = scanner.nextLine();
                String[] parts = input.split("\\s+");
                
                if (parts.length != 3) {
                    System.out.println("Invalid input. Please enter exactly three numbers.");
                    valid = false;
                    continue;
                }
                
                for (int i = 0; i < 3; i++) {
                    try {
                        int choice = Integer.parseInt(parts[i]);
                        if (choice < 1 || choice > 5) {
                            System.out.println("Invalid choice(s). Please pick only from the given list:");
                            valid = false;
                            break;
                        }
                        choices[i] = choice;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter numbers only.");
                        valid = false;
                        break;
                    }
                }
            } while (!valid);

            pizzaIngredients.clear();
            for (int choice : choices) {
                switch (choice) {
                    case 1: pizzaIngredients.add("Mushroom"); break;
                    case 2: pizzaIngredients.add("Paprika"); break;
                    case 3: pizzaIngredients.add("Sun-dried tomatoes"); break;
                    case 4: pizzaIngredients.add("Chicken"); break;
                    case 5: pizzaIngredients.add("Pineapple"); break;
                }
            }
            int sizeChoice;
            do {
                System.out.println("\nWhat size should your pizza be?");
                System.out.println("1. Large");
                System.out.println("2. Medium");
                System.out.println("3. Small");
                System.out.print("Enter only one choice (1, 2, or 3): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                sizeChoice = scanner.nextInt();
                scanner.nextLine();
                if (sizeChoice < 1 || sizeChoice > 3) {
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            } while (sizeChoice < 1 || sizeChoice > 3);
            System.out.print("\nDo you want extra cheese (Y/N): ");
            String extraCheese = scanner.nextLine();
            if (extraCheese.equalsIgnoreCase("Y")) {
                pizzaIngredients.add("Extra Cheese");
            }
            int sideDishChoice;
            do {
                System.out.println("\nFollowing are the side dish that go well with your pizza:");
                System.out.println("1. Calzone");
                System.out.println("2. Garlic bread");
                System.out.println("3. Chicken puff");
                System.out.println("4. Muffin");
                System.out.println("5. Nothing for me");
                System.out.print("What would you like? Pick one (1, 2, 3,…): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                sideDishChoice = scanner.nextInt();
                scanner.nextLine();
                if (sideDishChoice < 1 || sideDishChoice > 5) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } while (sideDishChoice < 1 || sideDishChoice > 5);

            sides.clear();
            switch (sideDishChoice) {
                case 1: sides.add("Calzone"); break;
                case 2: sides.add("Garlic bread"); break;
                case 3: sides.add("Chicken puff"); break;
                case 4: sides.add("Muffin"); break;
                case 5: break;
            }
            int drinkChoice;
            do {
                System.out.println("\nChoose from one of the drinks below. We recommend Coca Cola:");
                System.out.println("1. Coca Cola");
                System.out.println("2. Cold coffee");
                System.out.println("3. Cocoa Drink");
                System.out.println("4. No drinks for me");
                System.out.print("Enter your choice: ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                drinkChoice = scanner.nextInt();
                scanner.nextLine();
                if (drinkChoice < 1 || drinkChoice > 4) {
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } while (drinkChoice < 1 || drinkChoice > 4);

            drinks.clear();
            switch (drinkChoice) {
                case 1: drinks.add("Coca Cola"); break;
                case 2: drinks.add("Cold coffee"); break;
                case 3: drinks.add("Cocoa Drink"); break;
                case 4: break;
            }
            orderTotal = pizzaPrice;
            for (String side : sides) {
                if (sidePrices != null && sidePrices.containsKey(side)) {
                    orderTotal += sidePrices.get(side);
                }
            }
            for (String drink : drinks) {
                if (drinkPrices != null && drinkPrices.containsKey(drink)) {
                    orderTotal += drinkPrices.get(drink);
                }
            }
            System.out.print("\nWould you like the chance to pay only half for your order? (Y/N): ");
            String wantDiscount = scanner.nextLine();
            if (wantDiscount.equalsIgnoreCase("Y")) {
                isItYourBirthday();
            } else {
                makeCardPayment();
            }
            String[] parts = orderID.split("-");
            int num = Integer.parseInt(parts[2]);
            num++;
            orderID = parts[0] + "-" + parts[1] + "-" + num;
        }
    }

    public void isItYourBirthday() {
        try (Scanner scanner = new Scanner(System.in)) {
            LocalDate birthdate = null;
            do {
                System.out.print("\nEnter your birthday (yyyy-MM-dd): ");
                String birthdateStr = scanner.nextLine();
                try {
                    birthdate = LocalDate.parse(birthdateStr);
                    LocalDate fiveYearsAgo = LocalDate.now().minusYears(5);
                    LocalDate oneTwentyYearsAgo = LocalDate.now().minusYears(120);
                    if (birthdate.isAfter(fiveYearsAgo) || birthdate.isBefore(oneTwentyYearsAgo)) {
                        System.out.println("Invalid date. You are either too young or too dead to order. Please enter a valid date:");
                        birthdate = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter in yyyy-MM-dd format.");
                }
            } while (birthdate == null);

            LocalDate today = LocalDate.now();
            int age = Period.between(birthdate, today).getYears();
            if (age < 18 && birthdate.getMonth() == today.getMonth() && birthdate.getDayOfMonth() == today.getDayOfMonth()) {
                System.out.println("Congratulations! You pay only half the price for your order");
                orderTotal = orderTotal / 2;
            } else {
                System.out.println("Too bad! You do not meet the conditions to get our 50% discount");
            }
        }
    }

    public void makeCardPayment() {
        try (Scanner scanner = new Scanner(System.in)) {
            long cardNumber = 0;
            String cardNumberStr;
            do {
                System.out.print("\nEnter card number: ");
                while (!scanner.hasNextLong()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scanner.next();
                }
                cardNumber = scanner.nextLong();
                scanner.nextLine();
                cardNumberStr = Long.toString(cardNumber);
                if (cardNumberStr.length() != 14 || cardNumber == BLACKLISTED_NUMBER) {
                    System.out.println("Invalid card number. Please enter a 14-digit number that is not blacklisted.");
                }
            } while (cardNumberStr.length() != 14 || cardNumber == BLACKLISTED_NUMBER);

            String expiryDateStr;
            do {
                System.out.print("Enter card expiry date (MM/yy): ");
                expiryDateStr = scanner.nextLine();
                try {
                    int month = Integer.parseInt(expiryDateStr.substring(0, 2));
                    int year = Integer.parseInt(expiryDateStr.substring(3, 5)) + 2000;
                    LocalDate expiryDate = LocalDate.of(year, month, 1);
                    if (expiryDate.isBefore(LocalDate.now())) {
                        System.out.println("Invalid expiry date. Please enter a future date.");
                        expiryDateStr = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date format. Please enter in MM/yy format.");
                    expiryDateStr = null;
                }
            } while (expiryDateStr == null);

            int cvv;
            do {
                System.out.print("Enter card cvv (3 digits): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a 3-digit number.");
                    scanner.next();
                }
                cvv = scanner.nextInt();
                scanner.nextLine();
                if (cvv < 100 || cvv > 999) {
                    System.out.println("Invalid CVV. Please enter a 3-digit number.");
                }
            } while (cvv < 100 || cvv > 999);

            processCardPayment(cardNumber, expiryDateStr, cvv);
        }
    }

    public void processCardPayment(long cardNumber, String expiryDate, int cvv) {
        String cardNumberStr = Long.toString(cardNumber);
        int firstCardDigit = Integer.parseInt(cardNumberStr.substring(0, 1));
        String lastFourDigitsStr = cardNumberStr.substring(cardNumberStr.length() - 4);
        int lastFourDigits = Integer.parseInt(lastFourDigitsStr);

        StringBuilder cardNumberToDisplay = new StringBuilder();
        cardNumberToDisplay.append(cardNumberStr.charAt(0));
        for (int i = 1; i < cardNumberStr.length() - 4; i++) {
            cardNumberToDisplay.append('*');
        }
        cardNumberToDisplay.append(lastFourDigitsStr);

        System.out.println("\nCard processing details:");
        System.out.println("First card digit: " + firstCardDigit);
        System.out.println("Last four digits: " + lastFourDigits);
        System.out.println("Card number to display: " + cardNumberToDisplay);
        System.out.println("Expiry date: " + expiryDate);
        System.out.println("CVV: " + cvv);
        System.out.println("Payment processed successfully!");
    }

    @Override
    public String toString() {
        StringBuilder receipt = new StringBuilder();
        receipt.append("----- Receipt -----\n");
        receipt.append("Store Name: ").append(storeName).append("\n");
        receipt.append("Store Address: ").append(storeAddress).append("\n");
        receipt.append("Order ID: ").append(orderID).append("\n");
        receipt.append("Order Total: $").append(String.format("%.2f", orderTotal)).append("\n");
        receipt.append("-------------------");
        return receipt.toString();
    }
    
        public void makePizza() {
            throw new UnsupportedOperationException("Unimplemented method 'makePizza'");
        }
        
                public void specialOfTheDay(String string, String string2, String string3) {
                    throw new UnsupportedOperationException("Unimplemented method 'specialOfTheDay'");
                }
            }
            
            public class Pizza {
                public static void main(String[] args) {
                    List<String> pizzaIngredients = new ArrayList<>();
                    pizzaIngredients.add("Tomato Sauce");
                    pizzaIngredients.add("Cheese");
                    pizzaIngredients.add("Pepperoni");
                    List<String> sides = new ArrayList<>();
                    sides.add("Garlic Bread");
                    sides.add("Onion Rings");
                    Map<String, Double> sidePrices = new HashMap<>();
                    sidePrices.put("Garlic Bread", 3.0);
                    sidePrices.put("Onion Rings", 3.5);
                    List<String> drinks = new ArrayList<>();
                    drinks.add("Coke");
                    drinks.add("Sprite");
                    Map<String, Double> drinkPrices = new HashMap<>();
                    drinkPrices.put("Coke", 2.0);
                    drinkPrices.put("Sprite", 2.0);
                    
                    SliceoHeaven pizzeria = new SliceoHeaven("Slice - o - Heaven", "123 Pizza St", "info@sliceoheaven.com", "555-1234",
                            "Pizza, Sides, Drinks", pizzaIngredients, 10.0, sides, sidePrices, drinks, drinkPrices);
                    pizzeria.takeOrder();
                    
                    System.out.println("\n" + pizzeria); 
                    pizzeria.makePizza();
                pizzeria.specialOfTheDay("Margherita Pizza", "Fries", "12.99");
    }
}
