package machine;

import java.util.Scanner;

enum CoffeeStation {
    BUY,
    TAKE,
    FILL,
    REMAINING,
    EXIT
}

public class CoffeeMachine {
    static int[] contains = {400, 540, 120, 9, 550};
    static int[] espresso = {250, 0, 16, 1, 4};
    static int[] latte = {350, 75, 20, 1, 7};
    static int[] cappuccino = {200, 100, 12, 1, 6};
    static String[] component = {"water", "milk", "coffee beans", "cups"};
    static String[] filling = {"Write how many ml of water do you want to add:", "Write how many ml of milk do you want to add:", "Write how many grams of coffee beans do you want to add:", "Write how many disposable cups of coffee do you want to add:"};
    static String mainMenu = "Write action (buy, fill, take, remaining, exit):";
    static boolean end = false;

    static CoffeeStation coffeeStation;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!end) {
            System.out.println();
            System.out.println(mainMenu);
            input = scanner.next();
            while (!input.equals("buy") ^ !input.equals("fill") ^ !input.equals("take") ^ !input.equals("remaining") ^ !input.equals("exit")) {
                input = scanner.next();
            }
            CoffeeMachineState(input);
        }
    }

    public static void CoffeeMachineState(String action) {
        Scanner scanner = new Scanner(System.in);
        String input;
        boolean conditions;
        end = false;

        switch (action) {
            case "buy":
                coffeeStation = CoffeeStation.BUY;
                break;
            case "take":
                coffeeStation = CoffeeStation.TAKE;
                break;
            case "remaining":
                coffeeStation = CoffeeStation.REMAINING;
                break;
            case "fill":
                coffeeStation = CoffeeStation.FILL;
                break;
            case "exit":
                coffeeStation = CoffeeStation.EXIT;
                break;
        }

        switch (coffeeStation) {
            case BUY:
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                input = scanner.next();
                conditions = input.equals("1") ^ input.equals("2") ^ input.equals("3") ^ input.equals("back");
                while (!conditions) {
                    input = scanner.next();
                    conditions = input.equals("1") ^ input.equals("2") ^ input.equals("3") ^ input.equals("back");
                }
                if (input.equals("back")) {
                    break;
                }
                boolean condition;
                switch (input) {
                    case "1":
                        condition = true;
                        for (int i = 0; i < 4; i++) {
                            if (contains[i] - espresso[i] < 0) {
                                System.out.printf("Sorry, not enough %s!", component[i]);
                                System.out.println();
                                condition = false;
                                break;
                            }
                            contains[i] -= espresso[i];
                        }
                        if (condition) {
                            System.out.println("I have enough resources, making you a coffee!");
                            contains[4] += espresso[4];
                        }
                        break;
                    case "2":
                        condition = true;
                        for (int i = 0; i < 4; i++) {
                            if (contains[i] - latte[i] < 0) {
                                System.out.printf("Sorry, not enough %s!", component[i]);
                                System.out.println();
                                condition = false;
                                break;
                            }
                            contains[i] -= latte[i];
                        }
                        if (condition) {
                            System.out.println("I have enough resources, making you a coffee!");
                            contains[4] += latte[4];
                        }
                        break;
                    case "3":
                        condition = true;
                        for (int i = 0; i < 4; i++) {
                            if (contains[i] - cappuccino[i] < 0) {
                                System.out.printf("Sorry, not enough %s!", component[i]);
                                System.out.println();
                                condition = false;
                                break;
                            }
                            contains[i] -= cappuccino[i];
                        }
                        if (condition) {
                            System.out.println("I have enough resources, making you a coffee!");
                            contains[4] += cappuccino[4];
                        }
                        break;
                }
                break;
            case TAKE:
                System.out.printf("I gave you $%d", contains[4]);
                System.out.println();
                contains[4] = 0;
                break;
            case FILL:
                for (int i = 0; i < 4; i++) {
                    System.out.println(filling[i]);
                    input = scanner.next();
                    if (input.equals("back")) {
                        break;
                    } else {
                        contains[i] += Integer.parseInt(input);
                    }
                }
                break;
            case REMAINING:
                System.out.println();
                System.out.println("The coffee machine has:");
                System.out.printf("%d of water", contains[0]);
                System.out.println();
                System.out.printf("%d of milk", contains[1]);
                System.out.println();
                System.out.printf("%d of coffee beans", contains[2]);
                System.out.println();
                System.out.printf("%d of disposable cups", contains[3]);
                System.out.println();
                System.out.printf("%d of money", contains[4]);
                System.out.println();
                break;
            case EXIT:
                end = true;
                break;
        }
    }
}