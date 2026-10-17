
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Введіть перше число: ");
            double a = Double.parseDouble(
                    scanner.nextLine().trim().replace(',', '.')
            );

            System.out.print("Введіть друге число: ");
            double b = Double.parseDouble(
                    scanner.nextLine().trim().replace(',', '.')
            );

            System.out.print("Оберіть операцію (+, -, *, /): ");
            String operation = scanner.nextLine().trim();

            double result;

            switch (operation) {
                case "+":
                    result = calculator.add(a, b);
                    break;
                case "-":
                    result = calculator.subtract(a, b);
                    break;
                case "*":
                    result = calculator.multiply(a, b);
                    break;
                case "/":
                    result = calculator.divide(a, b);
                    break;
                default:
                    throw new InvalidInputException(
                            "Невідома операція: " + operation
                    );
            }

            System.out.println("Результат: " + result);

        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректні числа, а не текст.");

        } catch (InvalidInputException e) {
            System.out.println("Помилка введення: " + e.getMessage());

        } catch (ArithmeticException e) {
            System.out.println("Арифметична помилка: " + e.getMessage());

        } finally {
            System.out.println("Обробку запиту завершено.");
            scanner.close();
        }
    }
}
