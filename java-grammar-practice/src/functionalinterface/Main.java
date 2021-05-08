package functionalinterface;

public class Main {

    static class CalculatorService {
        public int calculate(Calculator calculator, int num1, int num2) {
            return calculator.calculate(num1, num2);
        }
    }

    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorService();
        Calculator addition = (num1, num2) -> num1 + num2;
        System.out.println(calculatorService.calculate(addition, 3, 6));

        Calculator multiply = (num1, num2) -> num1 * num2;
        System.out.println(calculatorService.calculate(multiply, 3, 6));
    }
}
