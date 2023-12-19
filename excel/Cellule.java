package excel;

public class Cellule {
    private String formuleTexte;
    private Double valeur;
    private EnumEtatCellule etatCellule;
    private Tree<String> arbre;

    public Cellule() {
        this.valeur = null;
        this.etatCellule = EnumEtatCellule.VIDE;
        this.arbre = new Tree<>();
    }

    public String getFormuleTexte() {
        return formuleTexte;
    }

    public void setFormuleTexte(String formuleTexte) {
        this.formuleTexte = formuleTexte;
        // Notifie les observateurs que la formule a été mise à jour
        //notifierObservateurs();
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
        this.etatCellule = etatCellule; // A MODIFIER
    }

    public Tree<String> getArbre() {
        return arbre;
    }

    public void setArbre(Tree<String> arbre) {
        this.arbre = arbre;
    }
}
