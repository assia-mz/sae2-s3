package v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import v2.EnumEtatCellule;
import v2.Tree;

public class Cellule{
    private DicoDesReferencesCellules DicoCell;
    private String name;
    private double valeurCell;
    private String formule;
    private Tree<String> arbre;
    private List<Cellule> listeDesDependances;
    private EnumEtatCellule etatCellule;


    public Cellule(String name, DicoDesReferencesCellules DicoCell) {
        this.name = name;
        this.DicoCell = DicoCell;
        this.listeDesDependances = new ArrayList<Cellule>();
        this.arbre = new Tree<String>();
        this.etatCellule = EnumEtatCellule.VIDE;
        this.formule = "";
    }

    // +--------------- méthodes de base ---------------+
    
    public void setFormule(String formule) {
        this.formule = formule;
    }

    public List<Cellule> getListeDesDependances(){
        return this.listeDesDependances;
    }
    
    
    public String getName(){
        return this.name;
    }

    public double getValeur(){
        return this.valeurCell;
    }

    public EnumEtatCellule getEtatCellule(){
        return this.etatCellule;
    }

    public String getFormule(){
        return this.formule;
    }

    public Tree<String> getArbre() {
        return arbre;
    }

    // +--------------- méthodes creation d'arbre ---------------+
    
    
    public void parcourirFormule() {
        String[] tokens = formule.split("\\s+");
        Stack<Node<String>> operandStack = new Stack<>();
        Stack<Node<String>> operatorStack = new Stack<>();
    
        for (String token : tokens) {
            Node<String> newNode;
    
            // Check if the token is a cell reference (e.g., B7, A3, E28)
            if (isCellReference(token)) {
                System.out.println("Il y a une référence");
                Cellule referencedCell = DicoCell.getCellule(token);
                if (referencedCell != null) {
                    addDependance(referencedCell);
                } else {
                    System.out.println("Cell not found for reference: " + token);
                }
                newNode = new Node<>(token, false);  // Assuming cell references are always on the left
            } else {
                newNode = new Node<>(token, true);  // Assuming non-cell references are always on the right
            }
    
            if (isOperator(token)) {
                operatorStack.push(newNode);
            } else {
                operandStack.push(newNode);
            }
        }
    
        while (!operatorStack.isEmpty()) {
            Node<String> operator = operatorStack.pop();
            Node<String> rightOperand = operandStack.pop();
            Node<String> leftOperand = operandStack.pop();
    
            // Ensure the root is always an operator
            Node<String> root = new Node<>(operator.getValue(), true);
            root.addSubNode(leftOperand);
            root.addSubNode(rightOperand);
    
            operandStack.push(root);
        }
    
        if (operandStack.size() != 1) {
            throw new IllegalArgumentException("Expression invalide : vérifiez votre expression.");
        }
    
        this.arbre.setRootNode(operandStack.pop());
    }
    

    private boolean isOperator(String token) {
        System.out.println(token);
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private boolean isCellReference(String token) {
        return token.matches("[A-Z]+\\d+");
    }
    
    public void showExpressionTree(Cellule cellule) {
        cellule.getArbre().showTree();
    }
    
    /*public void createTree(){
        //
    }*/

    // méthodes pour les dépendances
    public boolean addDependance(Cellule cell){
        System.out.println("Cellule dont je (" + this.getName() + ") dépend :" +  cell.getName());
        return this.listeDesDependances.add(cell);
    }

    private void resetDependances(Cellule cell){
		this.listeDesDependances.remove(cell);
	}

    // +-------------------- check circular dependencies --------------------+
    public boolean hasCircularDependency() {
        // Set to keep track of visited cells during DFS
        Set<Cellule> visited = new HashSet<>();

        // Set to keep track of cells in the current DFS path
        Set<Cellule> currentPath = new HashSet<>();

        // Check for circular dependencies starting from each cell
        for (Cellule cell : listeDesDependances) {
            if (!visited.contains(cell)) {
                if (hasCircularDependencyDFS(cell, visited, currentPath)) {
                    // Circular dependency found
                    return true;
                }
            }
        }

        // No circular dependency found
        return false;
    }

    private boolean hasCircularDependencyDFS(Cellule cell, Set<Cellule> visited, Set<Cellule> currentPath) {
        // Mark the current cell as visited and add to the current path
        visited.add(cell);
        currentPath.add(cell);

        // Explore dependencies
        for (Cellule dependency : cell.getListeDesDependances()) {
            if (!visited.contains(dependency)) {
                if (hasCircularDependencyDFS(dependency, visited, currentPath)) {
                    // Circular dependency found
                    return true;
                }
            } else if (currentPath.contains(dependency)) {
                // Circular dependency found in the current path
                return true;
            }
        }

        // Remove the current cell from the current path
        currentPath.remove(cell);

        return false;
    }


    // +-------------- méthode de calcule ----------------+

    public void evaluateCell() {
        this.valeurCell = evaluateNode(this.arbre.getRootNode());
    }


    private double evaluateNode(Node<String> node) {
        String value = node.getValue();
    
        if (isOperator(value)) {
            double operand1 = evaluateNode(node.getSubNodes().get(0));
            double operand2 = evaluateNode(node.getSubNodes().get(1));
    
            return performOperation(value, operand1, operand2);
        } else {
            // If the value is a cell reference, get its value
            if (isCellReference(value)) {
                Cellule referencedCell = DicoCell.getCellule(value); // Assuming DicoCell is an instance of DicoDesReferencesCellules
                if (referencedCell != null) {
                    return evaluateNode(referencedCell.getArbre().getRootNode());
                } else {
                    // Handle the case where the referenced cell is not found
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
                throw new IllegalArgumentException("Opérateur non pris en charge : " + operator);
        }
    }
    
    
}