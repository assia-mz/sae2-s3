package v5;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Cellule {
    private DicoDesReferencesCellules dicoCell;
    private String name;
    private double valeurCell;
    private String formule;
    private Tree<String> arbre;
    private EnumEtatCellule etatCellule;
    //private Set<Cellule> currentPath = new HashSet<>();

    public Cellule(String name, DicoDesReferencesCellules dicoCell) {
        this.name = name;
        this.dicoCell = dicoCell;
        this.arbre = new Tree<>();
        this.etatCellule = EnumEtatCellule.VIDE;
        this.formule = "";
    }

    

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public String getName() {
        return this.name;
    }

    public void notifyDependencies() {
        System.out.println("Notification");
    
        // Print information about all cells in the dictionary
        System.out.println("All Cells in the Dictionary:");
        for (Cellule cell : dicoCell.getDico().values()) {
            System.out.println(" - " + cell.getName());
            System.out.println("   Formula: " + cell.getFormule());
            System.out.println("   Value: " + cell.getValeur());
            //cell.printDependencies();
            // Add more information as needed
        }
    
        // Iterate through dependencies and update
        for (Cellule dependency : dicoCell.findCellsDependingOn(this)) {
            System.out.println("Il faut update");
    
            // Update the formula of the dependent cell
            dependency.setFormule(dependency.getFormule());
    
            // If the formula has changed, proceed with updates
            System.out.println("Formule actuelle : " + dependency.getFormule());

            // Traverse the formula of the dependent cell
            dependency.parcourirFormule();
    
            // Display the expression tree after traversing the formula
            System.out.println("Arbre après parcourirFormule:");
            dependency.showExpressionTree(dependency);
    
            // Evaluate the dependent cell
            dependency.evaluateCell();
    
            // Notify listeners about the update
                dependency.notifyDependencies();
        }
    }
    

    public void setValeur(double valeur) {
        this.valeurCell = valeur;
        notifyDependencies();
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

    public void parcourirFormule() {
        String[] tokens = formule.split("\\s+");
        Stack<Node<String>> stack = new Stack<>();
        int operandCount = 0;
        int operatorCount = 0;
    
        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            Node<String> newNode;
    
            if (isCellReference(token)) {
                Cellule referencedCell = dicoCell.getCellule(token);
                if (referencedCell != null) {
                    // Add dependency only if the referenced cell has a non-default formula
                    if (referencedCell.getFormule().equals("")) {
                        setIncorrectState();
                        return;
                    }
                    addDependance(referencedCell);
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
                    setIncorrectState();
                    return;
                }
    
                newNode.addSubNode(stack.pop());
                newNode.addSubNode(stack.pop());
            }
    
            stack.push(newNode);
        }
        this.etatCellule = EnumEtatCellule.CALCULABLE;
        System.out.println("Update calculable");
    
        if (operatorCount >= operandCount || stack.size() != 1) {
            setIncorrectState();
            return;
        }
        this.arbre.setRootNode(stack.pop());
    }
    
    private void setIncorrectState() {
        this.etatCellule = EnumEtatCellule.INCORRECTE;
    }
    
    
    

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    private boolean isCellReference(String token) {
        return token.matches("[A-I]+\\d+");
    }

    public void showExpressionTree(Cellule cellule) {
        cellule.getArbre().showTree();
    }

    private void addDependance(Cellule cell) {
        this.dicoCell.addDependencies(this.name, new Cellule[]{cell});
    }

    public boolean hasCircularDependency() {
        Set<Cellule> visited = new HashSet<>();
        Set<Cellule> currentPath = new HashSet<>();
        for (Cellule cell : this.dicoCell.getDico().values()) {
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
    
        // System.out.println("Checking cell: " + cell.getName());
        //this.dicoCell.printDependencies(); // Print dependencies
    
        Cellule[] dependencies = this.dicoCell.getDependencies(cell.getName());
        if (dependencies != null) {
            for (Cellule dependency : dependencies) {
                System.out.println("    Dependency: " + dependency.getName());
    
                if (!visited.contains(dependency)) {
                    System.out.println("        Visiting dependency for the first time.");
                    if (hasCircularDependencyDFS(dependency, visited, currentPath)) {
                        return true;
                    }
                } else if (currentPath.contains(dependency)) {
                    System.out.println("        Circular dependency detected!");
                    return true;
                }
            }
        }
    
        currentPath.remove(cell);
        return false;
    }
    

    public void evaluateCell() {
        this.valeurCell = evaluateNode(this.arbre.getRootNode());

        if (hasCircularDependency()) {
            this.etatCellule = EnumEtatCellule.INCORRECTE;
            throw new IllegalStateException("Dépendance circulaire détectée. Impossible d'évaluer la cellule " + this.getName());
        }
    }

    public void formulaChanged(String newFormula) {
        setFormule(newFormula);
        parcourirFormule();
        evaluateCell();
    
        // Set the state based on your business logic
        if (hasCircularDependency()) {
            this.etatCellule = EnumEtatCellule.INCORRECTE;
            throw new IllegalStateException("Dépendance circulaire détectée. Impossible d'évaluer la cellule " + this.getName());
        }
    
        // Add more conditions to set the state as needed
        // For example, if the formula is empty, set the state to VIDE
        if (newFormula.isEmpty()) {
            this.etatCellule = EnumEtatCellule.VIDE;
        } else {
            this.etatCellule = EnumEtatCellule.CALCULABLE;
        }
    
        notifyDependencies();
    }
    

    private double evaluateNode(Node<String> node) {
        String value = node.getValue();
        if (isOperator(value)) {
            double operand1 = evaluateNode(node.getSubNodes().get(0));
            double operand2 = evaluateNode(node.getSubNodes().get(1));
            return performOperation(value, operand1, operand2);
        } else {
            if (isCellReference(value)) {
                Cellule referencedCell = dicoCell.getCellule(value);
                if (referencedCell != null) {
                    return referencedCell.getValeur();
                } else {
                    throw new IllegalArgumentException("Cellule non trouvée pour la référence : " + value);
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
                    this.etatCellule = EnumEtatCellule.INCALCULABLE;
                    throw new ArithmeticException("Division par zéro");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Opérateur non pris en charge : " + operator);
        }
    }
}