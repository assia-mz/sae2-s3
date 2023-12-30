package v3;
/*
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Cellule2 {
    private CellDictionary cellDictionary;
    private String name;
    private double cellValue;
    private String formula;
    private Tree<String> expressionTree;
    private List<Cellule2> dependencies;
    private EnumEtatCellule cellState;
    private Set<Cellule2> currentPath = new HashSet<>();

    public Cellule2(String name, CellDictionary cellDictionary) {
        this.name = name;
        this.cellDictionary = cellDictionary;
        this.dependencies = new ArrayList<>();
        this.expressionTree = new Tree<>();
        this.cellState = EnumEtatCellule.VIDE;
        this.formula = "";
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public List<Cellule2> getDependencies() {
        return this.dependencies;
    }

    public String getName() {
        return this.name;
    }

    public void setCellValue(double value) {
        this.cellValue = value;
    }

    public double getCellValue() {
        return this.cellValue;
    }

    public EnumEtatCellule getCellState() {
        return this.cellState;
    }

    public String getFormula() {
        return this.formula;
    }

    public Tree<String> getExpressionTree() {
        return expressionTree;
    }

    public void traverseFormula() {
        String[] tokens = formula.split("\\s+");
        Stack<Node<String>> stack = new Stack<>();
        int operandCount = 0;
        int operatorCount = 0;

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            Node<String> newNode;

            if (isCellReference(token)) {
                System.out.println("Cell reference found: " + token);

                Cellule2 referencedCell = cellDictionary.getCellValue(token);
                if (referencedCell != null) {
                    addDependency(referencedCell);
                } else {
                    System.out.println("Cell not found for reference: " + token);
                }
                newNode = new Node<>(token, false);
                operandCount++;
            } else {
                newNode = new Node<>(token, true);
                operandCount++;
            }

            if (isOperator(token)) {
                operatorCount++;
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression: insufficient operands for the operator " + token + ". Please check your expression.");
                }

                newNode.addSubNode(stack.pop());
                newNode.addSubNode(stack.pop());
            }

            stack.push(newNode);
        }

        if (operatorCount >= operandCount || stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: check your expression.");
        }

        this.expressionTree.setRootNode(stack.pop());
    }

    private boolean isOperator(String token) {
        System.out.println(token);
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private boolean isCellReference(String token) {
        boolean result = token.matches("[A-I]+\\d+");
        System.out.println("Token: " + token + ", isCellReference: " + result);
        return result;
    }

    public void showExpressionTree(Cellule2 Cellule2) {
        Cellule2.getExpressionTree().showTree();
    }

    public boolean addDependency(Cellule2 cell) {
        System.out.println("Cell I depend on (" + this.getName() + "): " + cell.getName());
        System.out.println(this.dependencies.add(cell));
        return true;
    }

    private void resetDependencies(Cellule2 cell) {
        this.dependencies.remove(cell);
    }

    public boolean hasCircularDependency() {
        Set<Cellule2> visited = new HashSet<>();
        Set<Cellule2> currentPath = new HashSet<>();

        for (Cellule2 cell : dependencies) {
            if (!visited.contains(cell)) {
                if (hasCircularDependencyDFS(cell, visited, currentPath)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCircularDependencyDFS(Cellule2 cell, Set<Cellule2> visited, Set<Cellule2> currentPath) {
        visited.add(cell);
        currentPath.add(cell);

        for (Cellule2 dependency : cell.getDependencies()) {
            if (!visited.contains(dependency)) {
                if (hasCircularDependencyDFS(dependency, visited, currentPath)) {
                    return true;
                }
            } else if (currentPath.contains(dependency)) {
                return true;
            }
        }

        currentPath.remove(cell);
        return false;
    }

    public void evaluateCell() {
        this.cellValue = evaluateNode(this.expressionTree.getRootNode());
    }

    private double evaluateNode(Node<String> node) {
        String value = node.getValue();

        if (isOperator(value)) {
            double operand1 = evaluateNode(node.getSubNodes().get(0));
            double operand2 = evaluateNode(node.getSubNodes().get(1));

            return performOperation(value, operand1, operand2);
        } else {
            if (isCellReference(value)) {
                Cellule2 referencedCell = cellDictionary.getCellValue(value);
                if (referencedCell != null) {
                    return referencedCell.getCellValue();
                } else {
                    throw new IllegalArgumentException("Cell not found for reference: " + value);
                }
            } else {
                return Double.parseDouble(value);
            }
        }
    }

    private double performOperation(String operator, double operand1, double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    public void printDependencies() {
        System.out.println("Dependencies for cell " + this.getName() + ":");
        for (Cellule2 dependency : this.getDependencies()) {
            System.out.println(dependency.getName());
        }
    }
}
*/