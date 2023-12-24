package v2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import excel.EnumEtatCellule;
import excel.Tree;

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
    public String getName(){
        return this.name;
    }

    public double getValeur(){
        return this.Valeur;
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
    
    public void parcourirFormule(){
        String[] tokens = prefixExpression.split("\\s+");
        Stack<Node<String>> stack = new Stack<>();
        int operandCount = 0;
        int operatorCount = 0;

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            Node<String> newNode;

            // Check if the token is a cell reference (e.g., B7, A3, E28)
            if (isCellReference(token)) {
                System.out.println("Il y a une référence");
                this.addDependance(this.rechercheCellule());
                newNode = new Node<>(token);
                operandCount++;
            } else {
                newNode = new Node<>(token);
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
            throw new IllegalArgumentException("Expression invalide : vérifiez votre expression.");
        }

        expressionTree.setRootNode(stack.pop());
    }
    
    public void createTree(){
        //
    }






    // méthodes pour les dépendances
    public boolean addDependance(Cellule cell){
        System.out.println("Cellule dont je (" + this.getName() + ") dépend :" +  cell.getName());
        return this.listeDesDependances.add(cell);
    }

    private void resetDependances(Cellule cell){
		this.listeDesDependances.remove(cell);
	}
}