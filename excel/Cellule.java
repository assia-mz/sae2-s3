/*package excel;
import java.util.ArrayList;
import java.util.List;

public class Cellule {
    private List<String> references;
    private String formuleTexte;
    private Double valeur;
    private EnumEtatCellule etatCellule;
    private Tree<String> arbre;
    private String nameCell;

    public Cellule(char name, int n) {
        this.valeur = null;
        this.etatCellule = EnumEtatCellule.VIDE;
        this.arbre = new Tree<>();
        this.references = new ArrayList<>();
        this.nameCell = String.valueOf(name) + n;
    }

    public List<String> getReferences() {
        return references;
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

    public void detectCyclesInTree() {
        // Logic for checking cycles in the arbre
        updateEtatStatut(3);
    }

    public EnumEtatCellule updateEtatStatut(int statut) {
        System.out.println("statut: " + statut);
        switch (statut) {
            case 1:
                System.out.println("vide");
                return this.etatCellule = EnumEtatCellule.VIDE;
            case 2:
                System.out.println("calculable");
                return this.etatCellule = EnumEtatCellule.CALCULABLE;
            case 3:
                System.out.println("incalculable");
                return this.etatCellule = EnumEtatCellule.INCALCULABLE;
            case 4:
                System.out.println("incorrecte");
                return this.etatCellule = EnumEtatCellule.INCORRECTE;
            default:
                throw new IllegalArgumentException("PROBLEME!");
        }
    }

    public EnumEtatCellule getEtatCellule() {
        return this.etatCellule;
    }

    public Tree<String> getArbre() {
        return arbre;
    }

    public void setArbre(Tree<String> arbre) {
        this.arbre = arbre;
    }

    public void logCellCall() {
        if (!this.references.isEmpty()) {
            System.out.println("Cellule " + this.references.get(0) + " is called.");
        }
    }

    public void setReference(String reference) {
        this.references.add(reference);
    }
}
*/