package excel;

public class Cellule {

    private String formuleTexte;
    private Double valeur;
    private EnumEtatCellule etatCellule;
    private Tree<Node<String>> expressionTree;

    public Cellule() {
        this.valeur = null;
        this.etatCellule = EnumEtatCellule.VIDE;
        this.expressionTree = new Tree<>();
    }

    public Double evaluerFormule(String prefixExpression){
        PrefixCalculator calculator = new PrefixCalculator();
        calculator.buildExpressionTree(prefixExpression);
        calculator.showExpressionTree();
        this.expressionTree.setRootNode(calculator.getExpressionTree().getRootNode());
        return this.valeur = calculator.getResult();
    }

    public String getFormuleTexte() {
        return formuleTexte;
    }

    public void setFormuleTexte(String formuleTexte) {
        this.formuleTexte = formuleTexte;
    }

    public Double getValeur() {
        return valeur;
    }

    public void setValeur(Double valeur) {
        this.valeur = valeur;
    }

    public EnumEtatCellule getEtatCellule() {
        return etatCellule;
    }

    public void setEtatCellule(EnumEtatCellule etatCellule) {
        this.etatCellule = etatCellule;
    }

    public Tree<Node<String>> getExpressionTree() {
        return expressionTree;
    }

    public void setExpressionTree(Tree<Node<String>> expressionTree) {
        this.expressionTree = expressionTree;
    }
}
