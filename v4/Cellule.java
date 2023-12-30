package v4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Cellule {
    private DicoDesReferencesCellules dicoCell;
    private String name;
    private double valeurCell;
    private String formule;
    private Tree<String> arbre;
    private EnumEtatCellule etatCellule;
    private Set<Cellule> currentPath = new HashSet<>();
    private List<CelluleListener> listeners = new ArrayList<>();
    private List<Cellule> listeDesDependances;

    public Cellule(String name, DicoDesReferencesCellules dicoCell) {
        this.name = name;
        this.dicoCell = dicoCell;
        this.listeDesDependances = new ArrayList<>();
        this.arbre = new Tree<>();
        this.etatCellule = EnumEtatCellule.VIDE;
        this.formule = "";
    }

    public void setFormule(String formule) {
        this.formule = formule;
    }

    public List<Cellule> getDependencies() {
        return this.listeDesDependances;
    }

    public String getName() {
        return this.name;
    }

    public List<Cellule> getListeDesDependances(){
        return this.listeDesDependances;
    }

    public void addListener(CelluleListener listener) {
        listeners.add(listener);
    }

    public void removeListener(CelluleListener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (CelluleListener listener : listeners) {
            listener.onCellUpdated(this);
        }
    }

    public void setValeur(double valeur) {
        this.valeurCell = valeur;
        notifyListeners();
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
                    throw new IllegalArgumentException("Expression invalide : opérandes insuffisantes pour l'opérateur " + token + ". Veuillez vérifier votre expression.");
                }

                newNode.addSubNode(stack.pop());
                newNode.addSubNode(stack.pop());
            }

            stack.push(newNode);
        }

        if (operatorCount >= operandCount || stack.size() != 1) {
            throw new IllegalArgumentException("Expression invalide : veuillez vérifier votre expression.");
        }

        this.arbre.setRootNode(stack.pop());
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

    public void addDependance(Cellule cell) {
        this.listeDesDependances.add(cell);
    }

    /**
    /**
     * Vérifie s'il existe une dépendance circulaire pour la cellule.
     *
     * @return True s'il y a une dépendance circulaire, sinon False.
     */
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

        System.out.println("Checking cell: " + cell.getName());

        Cellule[] dependencies = this.dicoCell.getDependencies(cell.getName());
        if (dependencies != null) {
            System.out.println("dedans");
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
            throw new IllegalStateException("Dépendance circulaire détectée. Impossible d'évaluer la cellule " + this.getName());
        }
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
                    throw new ArithmeticException("Division par zéro");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Opérateur non pris en charge : " + operator);
        }
    }

    /*public void printDependencies(Cellule cell) {
        System.out.println("Dépendances pour la cellule " + cell.getName() + " :");
        for (Cellule dependency : cell.getListeDesDependances()) {
            System.out.println(dependency.getName());
        }
    }*/
}