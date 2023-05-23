package com.example.brewbuddycs380;

import java.util.*;

// Define an enum to represent the different properties that a coffee can have
enum Properties {
    HOT(0), ICED(1), STRONG(2), WEAK(3), SWEET(4),
    CREAMY(5), BLACK(6), FOAM(7), DECAF(8), BITTER(9),
    FLAVOR_FRUIT(10), FLAVOR_CHOCOLATE(11), FLAVOR_CARAMEL(12), FLAVOR_VANILLA(13);

    // Each property has an index value so it can be converted to a series of numbers to go in the database
    private final int index;

    Properties(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}

// Define a class to represent a coffee object
class Coffee implements Comparable<Coffee> {
    String name; // The name of the coffee
    int similarityScore; // The similarity score of the coffee with respect to the user's preference
    Set<Properties> coffeeProperties; // The properties of the coffee

    public String getName(){
        return name;
    }

    // Define an array of Coffee objects representing the different coffees available in the menu
    static Coffee[] MENU = new Coffee[]{
            new Coffee("Drip Coffee", EnumSet.of(Properties.STRONG, Properties.BLACK)),
            new Coffee("Espresso", EnumSet.of(Properties.STRONG, Properties.BLACK, Properties.BITTER)),
            new Coffee("Americano", EnumSet.of(Properties.WEAK, Properties.BLACK)),
            new Coffee("Latte", EnumSet.of(Properties.SWEET, Properties.CREAMY, Properties.WEAK)),
            new Coffee("Cappuccino", EnumSet.of(Properties.SWEET, Properties.CREAMY, Properties.FOAM)),
            new Coffee("Mocha", EnumSet.of(Properties.SWEET, Properties.CREAMY, Properties.FLAVOR_CHOCOLATE)),
            new Coffee("Macchiato", EnumSet.of(Properties.STRONG, Properties.CREAMY, Properties.FOAM)),
            new Coffee("Flat White", EnumSet.of(Properties.WEAK, Properties.CREAMY))
    };

    // Constructor to create a new Coffee object
    public Coffee(String name, Set<Properties> coffeeProperties) {
        this.name = name;
        this.coffeeProperties = coffeeProperties;
    }

    // Method to calculate the similarity score of the coffee with respect to the user's preference
    public int calculateSimilarityScore(Set<Properties> userPreference) {
        int similarityScore = 0;
        for (Properties property : userPreference) {
            if (coffeeProperties.contains(property)) {
                similarityScore++;
            }
        }
        return similarityScore;
    }

    // Return the difference between the similarity scores of the two coffees
    // A positive value means this coffee has a lower similarity score than the coffee being compared to
    // A negative value means this coffee has a higher similarity score than the coffee being compared to
    // Meaning when you poll() an item from the queue, the first will be the most similar, and so on
    @Override
    public int compareTo(Coffee coffee) {
        return coffee.similarityScore - this.similarityScore;
    }

}


// Define a class with static methods to recommend coffees based on the user's preference
public class CoffeeRecommender {
    public static Coffee[] recommendCoffee(Set<Properties> userPreference) {
        // Create a priority queue to store the recommended coffee objects
        // I chose this because it automatically sorts the coffee objects
        // based on similarityScore to the userPreferences
        PriorityQueue<Coffee> recommendedCoffees = new PriorityQueue<>();

        // Calculate the similarity score for each coffee object in the menu
        for (Coffee coffee : Coffee.MENU) {
            coffee.similarityScore = coffee.calculateSimilarityScore(userPreference);
            recommendedCoffees.add(coffee);
        }

        // Create an array to store the top 5 recommended coffee objects
        Coffee[] topRecommendedCoffees = new Coffee[5];
        for (int i = 0; i < topRecommendedCoffees.length; i++) {
            topRecommendedCoffees[i] = recommendedCoffees.poll();
        }
        return topRecommendedCoffees;
    }

    public static Coffee recommendTopCoffee(Set<Properties> userPreference) {
        // Create a priority queue to store the recommended coffee objects
        PriorityQueue<Coffee> recommendedCoffees = new PriorityQueue<>();

        // Calculate the similarity score for each coffee object in the menu
        for (Coffee coffee : Coffee.MENU) {
            coffee.similarityScore = coffee.calculateSimilarityScore(userPreference);
            recommendedCoffees.add(coffee);
        }

        // Return the top recommended coffee object
        return recommendedCoffees.poll();
    }

    // Method to return a String representation of the user prefernces
    public static String getPropertiesString(Set<Properties> userPreferences) {
        StringBuilder sb = new StringBuilder();
        for (Properties property : userPreferences) {
            switch (property) {
                case HOT:
                    sb.append("hot, ");
                    break;
                case ICED:
                    sb.append("iced, ");
                    break;
                case CREAMY:
                    sb.append("cream, ");
                    break;
                case SWEET:
                    sb.append("sugar, ");
                    break;
                case FLAVOR_FRUIT:
                    sb.append("strawberry syrup, ");
                    break;
                case FLAVOR_CHOCOLATE:
                    sb.append("chocolate syrup, ");
                    break;
                case FLAVOR_VANILLA:
                    sb.append("vanilla syrup, ");
                    break;
                case FLAVOR_CARAMEL:
                    sb.append("caramel syrup, ");
                    break;
                case DECAF:
                    sb.append("decaf, ");
                    break;
            }
        }
        // this part cuts off the last comma and space
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2);
        }
        return sb.toString();
    }

    // To store preferences in the database easier, this method converts the enums to a string representation
    // these correspond to the number of the enums, separate by a comma
    public static String userPreferenceToString(Set<Properties> userPreference) {
        StringBuilder sb = new StringBuilder();
        for (Properties property : userPreference) {
            sb.append(property.getIndex());
            sb.append(",");
        }
        // this part cuts off the last comma
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }

    public static Set<Properties> stringToUserPreference(String string) {
        // Creat en empty set of properties enums
        Set<Properties> userPreference = EnumSet.noneOf(Properties.class);
        String[] indices = string.split(",");
        for (String index : indices) {
            int i = Integer.parseInt(index);
            for (Properties property : Properties.values()) {
                if (property.getIndex() == i) {
                    userPreference.add(property);
                }
            }
        }
        return userPreference;
    }
}
