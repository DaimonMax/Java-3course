public abstract class Calculator {

    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль неможливе.");
        }
        return a / b;
    }

    public static double squareRoot(double a) throws InvalidInputException {
        if (a < 0) {
            throw new InvalidInputException("Не можна добути квадратний корінь з від'ємного числа.");
        }
        return Math.sqrt(a);
    }
}