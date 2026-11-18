import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("КАЛЬКУЛЯТОР");
        System.out.println("Для виходу з програми введіть 'exit'.\n");

        while (true) {
            System.out.print("Оберіть операцію (+, -, *, /, sqrt, exit): ");
            String operation = scanner.next();

            if (operation.equalsIgnoreCase("exit")) {
                System.out.println("Роботу калькулятора завершено.");
                break; 
            }

            try {
                if (!operation.equals("+") && !operation.equals("-") && 
                    !operation.equals("*") && !operation.equals("/") && 
                    !operation.equals("sqrt")) {
                    throw new InvalidInputException("Невідома операція. Спробуйте ще раз.");
                }

                if (operation.equals("sqrt")) {
                    System.out.print("Введіть число: ");
                    double num = scanner.nextDouble();
                    double result = Calculator.squareRoot(num);
                    System.out.println("Результат: " + result);
                } else {
                    System.out.print("Введіть перше число: ");
                    double num1 = scanner.nextDouble();

                    System.out.print("Введіть друге число: ");
                    double num2 = scanner.nextDouble();

                    double result = 0;
                    switch (operation) {
                        case "+":
                            result = Calculator.add(num1, num2);
                            break;
                        case "-":
                            result = Calculator.subtract(num1, num2);
                            break;
                        case "*":
                            result = Calculator.multiply(num1, num2);
                            break;
                        case "/":
                            result = Calculator.divide(num1, num2);
                            break;
                    }
                    System.out.println("Результат: " + result);
                }

            } catch (ArithmeticException e) {
                System.err.println(e.getMessage() + " Спробуйте знову.");
            } catch (InvalidInputException e) {
                System.err.println(e.getMessage());
            } catch (Exception e) {
                System.err.println("Необхідно вводити числа. Спробуйте знову.");
                scanner.nextLine(); 
            } finally {
                System.out.println("-----------------------------------------------");
            }
        }
        scanner.close();
    }
}