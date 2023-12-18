package excel;

public class Main {
    public static void main(String[] args) {
        PrefixCalculator calculator = new PrefixCalculator();
        String prefixExpression = "+ 2 - 3 6";
        
        calculator.buildExpressionTree(prefixExpression);
        calculator.showExpressionTree();
        
        double result = calculator.getResult();
        System.out.println("\nResult: " + result);
    }
}
