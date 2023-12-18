package excel;
import java.util.Stack;

public class PrefixCalculator {
    private Tree<String> expressionTree;

    public PrefixCalculator() {
        expressionTree = new Tree<>();
    }

    public void buildExpressionTree(String prefixExpression) throws IllegalArgumentException {
        String[] tokens = prefixExpression.split("\\s+");
        Stack<Node<String>> stack = new Stack<>();
        int operandCount = 0;
        int operatorCount = 0;

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            Node<String> newNode = new Node<>(token);

            if (isOperator(token)) {
                operatorCount++;
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: insufficient operands for operator " + token + ". Please consider checking your expression.");
                }

                newNode.addSubNode(stack.pop());
                newNode.addSubNode(stack.pop());
            } else {
                operandCount++;
            }

            stack.push(newNode);
        }

        if (operatorCount >= operandCount) {
            throw new IllegalArgumentException("Invalid expression: too many operators");
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: insufficient operators");
        }

        expressionTree.setRootNode(stack.pop());
    }

    public double getResult() {
        return evaluateNode(expressionTree.getRootNode());
    }

    private double evaluateNode(Node<String> node) {
        String value = node.getValue();

        if (isOperator(value)) {
            double operand1 = evaluateNode(node.getSubNodes().get(0));
            double operand2 = evaluateNode(node.getSubNodes().get(1));

            switch (value) {
                case "+":
                    return operand1 + operand2;
                case "-":
                    return operand1 - operand2;
                case "*":
                    return operand1 * operand2;
                case "/":
                    return operand1 / operand2;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + value);
            }
        } else {
            return Double.parseDouble(value);
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public void showExpressionTree() {
        expressionTree.showTree();
    }
}
