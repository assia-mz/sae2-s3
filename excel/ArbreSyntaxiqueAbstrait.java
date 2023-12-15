package excel;

import java.util.Stack;

public class ArbreSyntaxiqueAbstrait {

    // Attributs
    private NoeudAST racine;

    public void construireArbre(String formule) {
        racine = construireSousArbre(formule);
    }

    private NoeudAST construireSousArbre(String sousFormule) {
        return new NoeudAST(sousFormule);
    }

    // Méthode pour évaluer le nœud et retourner la valeur
    public double evaluer(String[] elements) {
        Stack<Double> stack = new Stack<>();

        for (int i = elements.length - 1; i >= 0; i--) {
            String element = elements[i];

            if (isOperator( )) {
                double operand1 = stack.pop();
                double operand2 = stack.pop();
                double result = applyOperator(element, operand1, operand2);
                stack.push(result);
            } else {
                stack.push(Double.parseDouble(element));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String element) {
        return element.equals("+") || element.equals("-") || element.equals("*") || element.equals("/");
    }

    private double applyOperator(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public Double evaluerArbre() {
        if (racine != null) {
            return evaluer();
        }
        return null;
    }
}
