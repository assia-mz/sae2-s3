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

    public void isCellCalled(){
        if (this.reference ==true){
            System.out.println("Reference dans cellule");
        }
    }

    public void cyclesInTree(){
        //if (..){ //verification des cycles
            updateEtatStatut(3);    
        //S}
    }

    /*public void updateEtatStatut(int statut) {
        if (statut == 4) {
            this.etatCellule = EnumEtatCellule.INCORRECTE;
        } else if (statut == 2) {
            this.etatCellule = EnumEtatCellule.CALCULABLE;
        } else if (statut == 3) {
            System.out.println("Votre cellule est dans un cycle ou divisée par 0");
            this.etatCellule = EnumEtatCellule.INCALCULABLE;
        } else if (statut==1) {
            this.etatCellule = EnumEtatCellule.VIDE;
        } else {
            throw new java.lang.RuntimeException("petit problème là");
        }
    }   */
    
    public EnumEtatCellule updateEtatStatut(int statut){
        System.out.println("statut :" + statut);
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
                throw new IllegalArgumentException("PROBLEME ! ");
        }
    }

    public EnumEtatCellule getEtatCellule(){
        return this.etatCellule;
    }

    public Tree<String> getArbre() {
        return arbre;
    }

    public void setArbre(Tree<String> arbre) {
        this.arbre = arbre;
    }
}
