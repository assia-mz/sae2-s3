package excel;
import java.util.Stack;

public class PrefixCalculator {
    private Tree<String> expressionTree;
    private int statut;
    private boolean reference = false;

    public PrefixCalculator() {
        expressionTree = new Tree<>();
        this.reference = false;
    }

    public void buildExpressionTree(String prefixExpression, Cellule cellule) throws IllegalArgumentException {
        String[] tokens = prefixExpression.split("\\s+");
        Stack<Node<String>> stack = new Stack<>();
        int operandCount = 0;
        int operatorCount = 0;
    
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
    
            Node<String> newNode;
    
            // Check if the token is a cell reference (e.g., B7, A3, E28)
            if (isCellReference(token)) {
                newNode = new Node<>(token);
                System.out.println("Y'EN A UN AGHHHHH");
                addObserver();
                operandCount++;
            } else {
                newNode = new Node<>(token);
                operandCount++;
            }
    
            if (isOperator(token)) {
                operatorCount++;
                if (stack.size() < 2) {
                    statut = 4;
                    throw new IllegalArgumentException("Expression invalide : opérandes insuffisantes pour l'opérateur " + token + ". Veuillez vérifier votre expression.");
                }
    
                newNode.addSubNode(stack.pop());
                newNode.addSubNode(stack.pop());
            }
    
            stack.push(newNode);
        }
    
        if (operatorCount >= operandCount) {
            statut = 4;
            throw new IllegalArgumentException("Expression invalide : trop d'opérateurs");
        }
    
        if (stack.size() != 1) {
            statut = 4;
            throw new IllegalArgumentException("Expression invalide : opérateurs insuffisants");
        }
    
        statut = 2;
        expressionTree.setRootNode(stack.pop());
        cellule.setArbre(expressionTree);
    }
    
    private boolean isCellReference(String token) {
        // Add logic to check if the token is a cell reference (e.g., B7, A3, E28)
        if (token.matches("[A-Z]+\\d+")){
            this.reference = true;
            return true;
        } else {
            return false;
        }
    }




    public double getResult(Cellule cellule) {
        return evaluateNode(cellule.getArbre().getRootNode());
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
                    throw new IllegalArgumentException("Opérateur non pris en charge : " + value);
            }
        } else {
            return Double.parseDouble(value);
        }
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    public void showExpressionTree(Cellule cellule) {
        cellule.getArbre().showTree();
    }

    public void addObserver(){
        System.out.println("J'ai été appelé");
    }

}
