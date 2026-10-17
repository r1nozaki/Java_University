
public class Calculator {

    public double add(double a, double b) throws InvalidInputException {
        validateNumbers(a, b);
        return a + b;
    }

    public double subtract(double a, double b) throws InvalidInputException {
        validateNumbers(a, b);
        return a - b;
    }

    public double multiply(double a, double b) throws InvalidInputException {
        validateNumbers(a, b);
        return a * b;
    }

    public double divide(double a, double b) throws InvalidInputException {
        validateNumbers(a, b);

        if (b == 0) {
            throw new ArithmeticException("Ділення на нуль заборонено.");
        }

        return a / b;
    }

    private void validateNumbers(double a, double b) throws InvalidInputException {
        if (!Double.isFinite(a) || !Double.isFinite(b)) {
            throw new InvalidInputException(
                    "Числа повинні бути скінченними (не NaN і не Infinity)."
            );
        }
    }
}
