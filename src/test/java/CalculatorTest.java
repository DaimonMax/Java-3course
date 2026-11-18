import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Assertions.assertEquals(5.0, Calculator.add(2.0, 3.0));
    }

    @Test
    public void testSubtract() {
        Assertions.assertEquals(1.0, Calculator.subtract(5.0, 4.0));
    }

    @Test
    public void testMultiply() {
        Assertions.assertEquals(12.0, Calculator.multiply(3.0, 4.0));
    }

    @Test
    public void testDivide() {
        Assertions.assertEquals(2.5, Calculator.divide(5.0, 2.0));
    }

    @Test
    public void testDivideByZero() {
        Assertions.assertThrows(ArithmeticException.class, () -> {
            Calculator.divide(10.0, 0.0);
        });
    }

    @Test
    public void testSquareRootInvalidInput() {
        Assertions.assertThrows(InvalidInputException.class, () -> {
            Calculator.squareRoot(-9.0);
        });
    }
}