package v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Cellule {
    private DicoDesReferencesCellules DicoCell;
    private String name;
    private double valeurCell;
    private String formule;
    private Tree<String> arbre;
    private List<Cellule> listeDesDependances;
    private EnumEtatCellule etatCellule;
    private Set<Cellule> currentPath = new HashSet<>();

    public Cellule(String name, DicoDesReferencesCellules DicoCell) {
        this.name = name;
        this.DicoCell = DicoCell;
        this.listeDesDependances = new ArrayList<>();
        this.arbre = new Tree<>();
        this.etatCellule = EnumEtatCellule.VIDE;
        this.formule = "";
    }

    // +--------------- Basic Methods ---------------+

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public List<Cellule> getListeDesDependances() {
        return this.listeDesDependances;
    }

    public String getName() {
        return this.name;
    }

    public void setValeur(double valeur) {
        this.valeurCell = valeur;
    }

    public double getValeur() {
        return this.valeurCell;
    }

    public EnumEtatCellule getEtatCellule() {
        return this.etatCellule;
    }

    public String getFormule() {
        return this.formule;
    }

    public Tree<String> getArbre() {
        return arbre;
    }

    // +--------------- Tree Creation Methods ---------------+

    public void parcourirFormule() {
        String[] tokens = formule.split("\\s+");
        Stack<Node<String>> stack = new Stack<>();
        int operandCount = 0;
        int operatorCount = 0;

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            Node<String> newNode;

            if (isCellReference(token)) {
                System.out.println("Cell reference found: " + token);
                Cellule referencedCell = DicoCell.getCellule(token);
                if (referencedCell != null) {
                    addDependance(referencedCell);
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
                    throw new IllegalArgumentException("Invalid expression: insufficient operands for operator " + token + ". Please check your expression.");
                }

                newNode.addSubNode(stack.pop());
                newNode.addSubNode(stack.pop());
            }

            stack.push(newNode);
        }

        if (operatorCount >= operandCount || stack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression: check your expression.");
        }

        this.arbre.setRootNode(stack.pop());
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private boolean isCellReference(String token) {
        boolean result = token.matches("[A-I]+\\d+");
        return result;
    }

    public void showExpressionTree(Cellule cellule) {
        cellule.getArbre().showTree();
    }

    public void addDependance(Cellule cell) {
        System.out.println(this.listeDesDependances.add(cell));
    }

    // +-------------------- Circular Dependency Check --------------------+

    public boolean hasCircularDependency() {
        Set<Cellule> visited = new HashSet<>();
        Set<Cellule> currentPath = new HashSet<>();
        for (Cellule cell : this.listeDesDependances) {
            if (!visited.contains(cell)) {
                if (hasCircularDependencyDFS(cell, visited, currentPath)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasCircularDependencyDFS(Cellule cell, Set<Cellule> visited, Set<Cellule> currentPath) {
        visited.add(cell);
        currentPath.add(cell);

        for (Cellule dependency : cell.getListeDesDependances()) {
            if (!visited.contains(dependency)) {
                if (hasCircularDependencyDFS(dependency, visited, currentPath)) {
                    return true;
                }
            } else if (currentPath.contains(dependency)) {
                return true;
            }
        }

        return false;
    }

    // +-------------- Calculation Method ----------------+

    public void evaluateCell() {
        this.valeurCell = evaluateNode(this.arbre.getRootNode());
        printDependencies();
    }

    private double evaluateNode(Node<String> node) {
        String value = node.getValue();

        if (isOperator(value)) {
            double operand1 = evaluateNode(node.getSubNodes().get(0));
            double operand2 = evaluateNode(node.getSubNodes().get(1));

            return performOperation(value, operand1, operand2);
        } else {
            if (isCellReference(value)) {
                Cellule referencedCell = DicoCell.getCellule(value);
                if (referencedCell != null) {
                    return referencedCell.getValeur();
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
        for (Cellule dependency : this.getListeDesDependances()) {
            System.out.println(dependency.getName());
        }
    }

}
