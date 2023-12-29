package v2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * La classe Cellule représente une cellule dans un tableau de feuilles de calcul.
 * Elle gère la formule associée, les dépendances, et permet l'évaluation de la cellule.
 */
public class Cellule {
    private DicoDesReferencesCellules DicoCell;
    private String name;
    private double valeurCell;
    private String formule;
    private Tree<String> arbre;
    private List<Cellule> listeDesDependances;
    private EnumEtatCellule etatCellule;
    private Set<Cellule> currentPath = new HashSet<>();
    private List<CelluleListener> listeners = new ArrayList<>();

    /**
     * Constructeur de la classe Cellule.
     *
     * @param name     Nom de la cellule.
     * @param DicoCell Dictionnaire des références des cellules.
     */
    public Cellule(String name, DicoDesReferencesCellules DicoCell) {
        this.name = name;
        this.DicoCell = DicoCell;
        this.listeDesDependances = new ArrayList<>();
        this.arbre = new Tree<>();
        this.etatCellule = EnumEtatCellule.VIDE;
        this.formule = "";
    }

    /**
     * Définit la formule associée à la cellule.
     *
     * @param formule Formule à définir.
     */
    public void setFormule(String formule) {
        this.formule = formule;
    }

    /**
     * Obtient la liste des dépendances de la cellule.
     *
     * @return Liste des dépendances.
     */
    public List<Cellule> getListeDesDependances() {
        return this.listeDesDependances;
    }

    /**
     * Obtient le nom de la cellule.
     *
     * @return Nom de la cellule.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Ajoute un auditeur (listener) à la cellule pour être notifié des mises à jour.
     *
     * @param listener Auditeur à ajouter.
     */
    public void addListener(CelluleListener listener) {
        listeners.add(listener);
    }

    /**
     * Supprime un auditeur (listener) de la cellule.
     *
     * @param listener Auditeur à supprimer.
     */
    public void removeListener(CelluleListener listener) {
        listeners.remove(listener);
    }

    /**
     * Notifie tous les auditeurs en cas de mise à jour de la cellule.
     */
    private void notifyListeners() {
        for (CelluleListener listener : listeners) {
            listener.onCellUpdated(this);
        }
    }

    /**
     * Définit la valeur de la cellule et notifie les auditeurs.
     *
     * @param valeur Nouvelle valeur de la cellule.
     */
    public void setValeur(double valeur) {
        this.valeurCell = valeur;
        notifyListeners();
    }

    /**
     * Obtient la valeur actuelle de la cellule.
     *
     * @return Valeur de la cellule.
     */
    public double getValeur() {
        return this.valeurCell;
    }

    /**
     * Obtient l'état actuel de la cellule.
     *
     * @return État de la cellule.
     */
    public EnumEtatCellule getEtatCellule() {
        return this.etatCellule;
    }

    /**
     * Obtient la formule associée à la cellule.
     *
     * @return Formule de la cellule.
     */
    public String getFormule() {
        return this.formule;
    }

    /**
     * Obtient l'arbre d'expression associé à la cellule.
     *
     * @return Arbre d'expression.
     */
    public Tree<String> getArbre() {
        return arbre;
    }

    /**
     * Parcourt la formule et construit l'arbre d'expression correspondant.
     */
    public void parcourirFormule() {
        String[] tokens = formule.split("\\s+");
        Stack<Node<String>> stack = new Stack<>();
        int operandCount = 0;
        int operatorCount = 0;

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            Node<String> newNode;

            if (isCellReference(token)) {
                Cellule referencedCell = DicoCell.getCellule(token);
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

    /*
     * 
     * @param an element from the formula (e.g. an operator like "+" or a number like 1)
     * 
     * @return true if the token is an operator
     * 
     */
    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/");
    }

    /*
     * returns true if the parameter is apparently a reference to another cell
     * 
     * @return boolean 
     */
    private boolean isCellReference(String token) {
        boolean result = token.matches("[A-I]+\\d+");
        return result;
    }

    /**
     * Affiche l'arbre d'expression pour la cellule spécifiée.
     *
     * @param cellule Cellule pour laquelle afficher l'arbre.
     */
    public void showExpressionTree(Cellule cellule) {
        cellule.getArbre().showTree();
    }

    /**
     * Ajoute une dépendance à la cellule.
     *
     * @param cell Cellule dépendante.
     */
    public void addDependance(Cellule cell) {
        this.listeDesDependances.add(cell);
    }

    /**
     * Vérifie s'il existe une dépendance circulaire pour la cellule.
     *
     * @return True s'il y a une dépendance circulaire, sinon False.
     */
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

    /*
     * a corriger
     */
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

    /**
     * Évalue la cellule en fonction de sa formule.
     */
    public void evaluateCell() {
        if (hasCircularDependency()) {
            throw new IllegalStateException("Dépendance circulaire détectée. Impossible d'évaluer la cellule " + this.getName());
        }

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
                    throw new IllegalArgumentException("Cellule non trouvée pour la référence : " + value);
                }
            } else {
                return Double.parseDouble(value);
            }
        }
    }

    /*
     * Fait le calcul entre les deux parametres selon l'operateur
     * 
     * @param string operateur
     * @param double première valeur
     * @param double deuxième valeur
     * @return le resultat du calcul
     * 
     */
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

    /**
     * Affiche les dépendances de la cellule.
     */
    public void printDependencies() {
        System.out.println("Dépendances pour la cellule " + this.getName() + " :");
        for (Cellule dependency : this.getListeDesDependances()) {
            System.out.println(dependency.getName());
        }
    }
}
