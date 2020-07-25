package machine;

import java.util.Scanner;

public class CoffeeMachine {
    public static void contains (int[] ingredients) {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d of water", ingredients[0]);
        System.out.println();
        System.out.printf("%d of milk", ingredients[1]);
        System.out.println();
        System.out.printf("%d of coffee beans", ingredients[2]);
        System.out.println();
        System.out.printf("%d of disposable cups", ingredients[3]);
        System.out.println();
        System.out.printf("%d of money", ingredients[4]);
        System.out.println();
    }

    public static int[] buy () {
        Scanner scanner = new Scanner(System.in);

        int[] ingredients = {0, 0, 0, 0, 0};

        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        String input = scanner.next();

        if (!input.equals("back")) {
            while (!input.equals("1") ^ !input.equals("2") ^ !input.equals("3")) {
                input = scanner.next();
                if (input.equals("back")) {
                    break;
                }
            }
            switch (input) {
                case "1":
                    ingredients[0] = 250;
                    ingredients[2] = 16;
                    ingredients[3] = 1;
                    ingredients[4] = 4;
                    break;
                case "2":
                    ingredients[0] = 350;
                    ingredients[1] = 75;
                    ingredients[2] = 20;
                    ingredients[3] = 1;
                    ingredients[4] = 7;
                    break;
                case "3":
                    ingredients[0] = 200;
                    ingredients[1] = 100;
                    ingredients[2] = 12;
                    ingredients[3] = 1;
                    ingredients[4] = 6;
                    break;
            }
        }

        return ingredients;
    }

    public static int[] fill() {
        Scanner scanner = new Scanner(System.in);

        int[] add = new int[4];

        System.out.println("Write how many ml of water do you want to add:");
        String input = scanner.next();
        if (!input.equals("back")) {
            add[0] = Integer.parseInt(input);
            System.out.println("Write how many ml of milk do you want to add:");
            input = scanner.next();
            if (!input.equals("back")) {
                add[1] = Integer.parseInt(input);
                System.out.println("Write how many grams of coffee beans do you want to add:");
                input = scanner.next();
                if (!input.equals("back")) {
                    add[2] = Integer.parseInt(input);
                    System.out.println("Write how many disposable cups of coffee do you want to add:");
                    input = scanner.next();
                    if (!input.equals("back")) {
                        add[3] = Integer.parseInt(input);
                    }
                }
            }
        }
        return add;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] machineContains = {400, 540, 120, 9, 550};


        String intro = "Write action (buy, fill, take, remaining, exit):";

        System.out.println();

        System.out.println(intro);
        String action = scanner.next();
        boolean buy;
        boolean fill;
        boolean take;

        while (!action.equals("exit")) {
            buy = action.equals("buy");
            fill = action.equals("fill");
            take = action.equals("take");

            while (!buy ^ !fill ^ !take ^ action.equals("remaining")) {
                action = scanner.next();
                buy = action.equals("buy");
                fill = action.equals("fill");
                take = action.equals("take");
            }

            switch (action) {
                case "buy":
                    int[] ingredients = buy();
                    boolean enoughIngredients = true;
                    String component = "";

                    for (int i = 0; i < 4; i++) {
                        if ((machineContains[i] - ingredients[i]) < 0) {
                            enoughIngredients = false;
                            if (i == 0) {
                                component = "water";
                            } else if (i == 1) {
                                component = "milk";
                            } else if (i == 2) {
                                component = "coffee beans";
                            } else {
                                component = "cups";
                            }
                            System.out.printf("Sorry, not enough %s!", component);
                            break;
                        }
                        machineContains[i] -= ingredients[i];
                    }
                    if (enoughIngredients) {
                        machineContains[4] += ingredients[4];
                    }
                    break;

                case "fill":
                    int[] filling = fill();

                    for (int i = 0; i < 4; i++) {
                        machineContains[i] += filling[i];
                    }
                    break;

                case "take":
                    System.out.printf("I gave you $%d", machineContains[4]);
                    System.out.println();

                    machineContains[4] = 0;
                    break;

                case "remaining":
                    contains(machineContains);
            }

            System.out.println();
            System.out.println(intro);
            action = scanner.next();
        }
    }
}
